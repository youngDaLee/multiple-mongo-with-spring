package com.goatking91.multiplemongo.repository.db1;

import com.goatking91.multiplemongo.model.db1.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    Optional<User> findByName(String name);
    List<User> findByNameContains(String name);
    List<User> findByIdIn(List<ObjectId> ids);

}
