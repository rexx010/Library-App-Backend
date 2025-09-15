package com.rexxempire.services;

import com.rexxempire.data.models.BorrowBook;

import java.util.List;

public interface BorrowBookService {
    BorrowBook borrowBook(String userId, String bookId);
    List<BorrowBook> viewBorrowedBookByUserId(String userId);
    BorrowBook returnBook(String userId, String bookId);
}
