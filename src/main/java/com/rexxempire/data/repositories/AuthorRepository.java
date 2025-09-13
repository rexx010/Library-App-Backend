package com.rexxempire.data.repositories;

import com.rexxempire.data.models.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository <Author, String>{
    Optional<Author> findById(String id);
}
