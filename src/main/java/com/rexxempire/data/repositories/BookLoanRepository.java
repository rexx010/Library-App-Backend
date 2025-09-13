package com.rexxempire.data.repositories;

import com.rexxempire.data.models.BookLoan;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookLoanRepository extends MongoRepository <BookLoan, String>{
    Optional<BookLoan> findByUserId(String userId);
}
