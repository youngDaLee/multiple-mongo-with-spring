package com.goatking91.multiplemongo.service;

import com.goatking91.multiplemongo.dto.ArticleCreateDto;
import com.goatking91.multiplemongo.dto.ArticleGetDto;
import com.goatking91.multiplemongo.dto.ArticleGetResult;
import com.goatking91.multiplemongo.model.db1.User;
import com.goatking91.multiplemongo.model.db2.Article;
import com.goatking91.multiplemongo.repository.db1.UserRepository;
import com.goatking91.multiplemongo.repository.db2.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    public ArticleGetResult get(ArticleGetDto articleGetDto, Pageable pageable) {
        Page<Article> articles;

        if (articleGetDto.getKey().equals("title")) {
            articles = articleRepository.findByTitle(articleGetDto.getValue(), pageable);
        } else if (articleGetDto.getKey().equals("content")) {
            articles = articleRepository.findByContentLike(articleGetDto.getValue(), pageable);
        } else {
            articles = articleRepository.findAll(pageable);
        }

        List<ObjectId> userIds = articles.stream().map(Article::getUserId).collect(Collectors.toList());
        List<User> userList = userRepository.findByIdIn(userIds);

        Page<ArticleGetResult.ArticleData> data = articles.map(article -> {
            User user = userList.stream().filter(user1 -> user1.getId().equals(article.getUserId())).findFirst().orElseThrow();
            return ArticleGetResult.ArticleData.builder()
                    .id(article.getId().toString())
                    .userName(user.getName())
                    .title(article.getTitle())
                    .content(article.getContent())
                    .build();
        });

        return ArticleGetResult.builder().data(data).build();
    }

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
