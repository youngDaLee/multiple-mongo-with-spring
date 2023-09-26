package com.goatking91.multiplemongo.repository.db2;

import com.goatking91.multiplemongo.model.db2.Article;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepository extends MongoRepository<Article, ObjectId> {
}
