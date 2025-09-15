package com.rexxempire.data.repositories;

import com.rexxempire.data.models.BorrowBook;
import com.rexxempire.data.models.Status;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowBookRepository extends MongoRepository <BorrowBook, String>{
    List<BorrowBook> findByUserId(String userId);
    Optional<BorrowBook> findByUserIdAndBookIdAndStatus(String userId, String bookId, Status status);
    Optional<BorrowBook> findFirstByUserIdAndBookIdAndStatus(String userId, String bookId, Status status);
}
