package com.journalLatest.Repository;

import com.journalLatest.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String userName);
    User deleteByUserName(String userName);
}
