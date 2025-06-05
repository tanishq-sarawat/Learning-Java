package com.example.demo.repository;

import com.example.demo.Entity.Entry;
import com.example.demo.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUserName(String userName);
}
