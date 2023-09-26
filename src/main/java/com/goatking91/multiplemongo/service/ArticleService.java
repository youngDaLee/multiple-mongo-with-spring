package com.goatking91.multiplemongo.service;

import com.goatking91.multiplemongo.dto.ArticleCreateDto;
import com.goatking91.multiplemongo.dto.UserCreateDto;
import com.goatking91.multiplemongo.model.db1.User;
import com.goatking91.multiplemongo.model.db2.Article;
import com.goatking91.multiplemongo.repository.db1.UserRepository;
import com.goatking91.multiplemongo.repository.db2.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    public ArticleCreateDto create(ArticleCreateDto articleCreateDto) {
        User user = userRepository.findByName(articleCreateDto.getUsername()).orElseThrow();
        Article article = articleRepository.save(
                Article.builder()
                        .userId(user.getId())
                        .title(articleCreateDto.getTitle())
                        .content(articleCreateDto.getContent())
                        .build()
        );

        return ArticleCreateDto.builder().username(user.getName()).title(article.getTitle()).content(article.getContent()).build();
    }
}
