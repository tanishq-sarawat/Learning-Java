package com.example.demo.repository;

import com.example.demo.Entity.Entry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EntryRepository extends MongoRepository<Entry, String> {
}
