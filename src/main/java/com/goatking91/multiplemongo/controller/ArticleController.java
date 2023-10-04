package com.goatking91.multiplemongo.controller;

import com.goatking91.multiplemongo.dto.ArticleCreateDto;
import com.goatking91.multiplemongo.dto.ArticleGetDto;
import com.goatking91.multiplemongo.dto.ArticleGetResult;
import com.goatking91.multiplemongo.dto.ArticleUpdateDto;
import com.goatking91.multiplemongo.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/v1/api/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("")
    public ResponseEntity<ArticleGetResult> get(ArticleGetDto articleGetDto, Pageable pageable) {
        return ResponseEntity.ok(articleService.get(articleGetDto, pageable));
    }

    @PostMapping("")
    public ResponseEntity<ArticleCreateDto> create(@RequestBody ArticleCreateDto articleCreateDto) {
        return ResponseEntity.created(URI.create("v1/api/users")).body(articleService.create(articleCreateDto));
    }

    @PutMapping("")
    public ResponseEntity<ArticleUpdateDto> update(@RequestBody ArticleUpdateDto articleUpdateDto) {
        return ResponseEntity.created(URI.create("v1/api/users")).body(articleService.update(articleUpdateDto));
    }
}
