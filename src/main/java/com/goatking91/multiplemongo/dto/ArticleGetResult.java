package com.goatking91.multiplemongo.dto;

import com.goatking91.multiplemongo.model.db2.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleGetResult {
    Slice<ArticleData> data;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ArticleData {
        private String id;
        private String userName;
        private String title;
        private String content;
    }
}
