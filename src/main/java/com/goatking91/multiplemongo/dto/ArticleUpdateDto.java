package com.goatking91.multiplemongo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleUpdateDto {
    private String articleId;
    private String username;
    private String title;
    private String content;
}
