package com.rexxempire.data.repositories;

import com.rexxempire.data.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {
    Optional<Book> findById(String id);
}
