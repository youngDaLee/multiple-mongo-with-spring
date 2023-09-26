package com.goatking91.multiplemongo.config.mongodb.db2;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@EnableMongoRepositories(basePackages = {"com.goatking91.multiplemongo.repository.db2"},
        mongoTemplateRef = Db2MongoConfig.MONGO_TEMPLATE
)
public class Db2MongoConfig {
    protected static final String MONGO_TEMPLATE = "db2MongoTemplate";
    @Bean(name = "db2Properties")
    @ConfigurationProperties(prefix = "spring.data.mongodb.db2")
    public MongoProperties getDb2Props() {
        return new MongoProperties();
    }

    @Bean(name = "db2MongoClient")
    public MongoClient mongoClient(@Qualifier("db2Properties") MongoProperties mongoProperties) {

        MongoCredential credential = MongoCredential
                .createCredential(mongoProperties.getUsername(), mongoProperties.getAuthenticationDatabase(), mongoProperties.getPassword());

        return MongoClients.create(MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder
                        .hosts(singletonList(new ServerAddress(mongoProperties.getHost(), mongoProperties.getPort()))))
                .credential(credential)
                .build());
    }

    @Bean(name = "db2MongoDBFactory")
    public MongoDatabaseFactory mongoDatabaseFactory(
            @Qualifier("db2MongoClient") MongoClient mongoClient,
            @Qualifier("db2Properties") MongoProperties mongoProperties) {
        return new SimpleMongoClientDatabaseFactory(mongoClient, mongoProperties.getDatabase());
    }

    @Bean(name = "db2MappingMongoConverter")
    public MappingMongoConverter mappingMongoConverter(@Qualifier("db2MongoDBFactory") MongoDatabaseFactory mongoDatabaseFactory,
                                                       MongoMappingContext mappingContext) {
        DefaultDbRefResolver resolver = new DefaultDbRefResolver(mongoDatabaseFactory);
        MappingMongoConverter converter = new MappingMongoConverter(resolver, mappingContext);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        return converter;
    }

    @Bean(name = Db2MongoConfig.MONGO_TEMPLATE)
    public MongoTemplate mongoTemplate(@Qualifier("db2MongoDBFactory") MongoDatabaseFactory mongoDatabaseFactory,
                                       @Qualifier("db2MappingMongoConverter") MappingMongoConverter mongoConverter) {
        return new MongoTemplate(mongoDatabaseFactory, mongoConverter);
    }
}
