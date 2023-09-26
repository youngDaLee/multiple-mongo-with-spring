package com.goatking91.multiplemongo.model.db1;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "USER")
public class User {
    @Id
    private ObjectId id;
    private String name;
}
