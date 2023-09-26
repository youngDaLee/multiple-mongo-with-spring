package com.goatking91.multiplemongo.config.mongodb.db1;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import static java.util.Collections.singletonList;


@Configuration
@EnableMongoRepositories(basePackages = {"com.goatking91.multiplemongo.repository.db1"},
        mongoTemplateRef = Db1MongoConfig.MONGO_TEMPLATE
)
@EnableConfigurationProperties
public class Db1MongoConfig {
    protected static final String MONGO_TEMPLATE = "db1MongoTemplate";

    @Primary
    @Bean(name = "db1Properties")
    @ConfigurationProperties(prefix = "spring.data.mongodb.db1")
    public MongoProperties getDb1Props() {
        return new MongoProperties();
    }

    @Primary
    @Bean(name = "db1MongoClient")
    public MongoClient mongoClient(@Qualifier("db1Properties") MongoProperties mongoProperties) {

        MongoCredential credential = MongoCredential
                .createCredential(mongoProperties.getUsername(), mongoProperties.getAuthenticationDatabase(), mongoProperties.getPassword());

        return MongoClients.create(MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder
                        .hosts(singletonList(new ServerAddress(mongoProperties.getHost(), mongoProperties.getPort()))))
                .credential(credential)
                .build());
    }

    @Primary
    @Bean(name = "db1MongoDBFactory")
    public MongoDatabaseFactory mongoDatabaseFactory(
            @Qualifier("db1MongoClient") MongoClient mongoClient,
            @Qualifier("db1Properties") MongoProperties mongoProperties) {
        return new SimpleMongoClientDatabaseFactory(mongoClient, mongoProperties.getDatabase());
    }

    @Primary
    @Bean(name = "db1MappingMongoConverter")
    public MappingMongoConverter mappingMongoConverter(@Qualifier("db1MongoDBFactory") MongoDatabaseFactory mongoDatabaseFactory,
                                                       MongoMappingContext mappingContext) {
        DefaultDbRefResolver resolver = new DefaultDbRefResolver(mongoDatabaseFactory);
        MappingMongoConverter converter = new MappingMongoConverter(resolver, mappingContext);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        return converter;
    }

    @Primary
    @Bean(name = Db1MongoConfig.MONGO_TEMPLATE)
    public MongoTemplate mongoTemplate(@Qualifier("db1MongoDBFactory") MongoDatabaseFactory mongoDatabaseFactory,
                                       @Qualifier("db1MappingMongoConverter") MappingMongoConverter mongoConverter) {
        return new MongoTemplate(mongoDatabaseFactory, mongoConverter);
    }
}
