package com.goatking91.multiplemongo.repository.db2;

import com.goatking91.multiplemongo.model.db2.Article;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ArticleRepository extends MongoRepository<Article, ObjectId> {
    Page<Article> findByTitle(String title, Pageable pageable);

    Page<Article> findByContentLike(String content, Pageable pageable);

    @Query("{content : { '$regex': ?0 }}")
    Page<Article> findByContent(String content, Pageable pageable);
}
