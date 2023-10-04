package com.goatking91.multiplemongo.dto;

import com.goatking91.multiplemongo.model.db2.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleGetResult {
    Page<Article> data;
}
