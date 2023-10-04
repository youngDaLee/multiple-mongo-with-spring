package com.goatking91.multiplemongo.repository.db2;

import com.goatking91.multiplemongo.model.db2.Article;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ArticleRepository extends MongoRepository<Article, ObjectId> {
    // count 로직 제외하고 페이지네이션(count를 위한 aggregation 과정 건너뜀)
    Slice<Article> findByTitle(String title, Pageable pageable);

    Slice<Article> findByContentLike(String content, Pageable pageable);

    @Query("{}")
    Slice<Article> findArticles(Pageable pageable);

    @Query("{content : { '$regex': ?0 }}")
    Page<Article> findByContent(String content, Pageable pageable);
}
