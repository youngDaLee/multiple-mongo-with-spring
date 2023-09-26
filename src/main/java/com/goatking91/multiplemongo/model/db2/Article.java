package com.goatking91.multiplemongo.model.db2;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "ARTICLE")
public class Article {
    @Id
    private ObjectId id;
    private ObjectId userId;
    private String title;
    private String content;
}
