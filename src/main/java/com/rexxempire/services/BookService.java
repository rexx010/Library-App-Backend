package com.rexxempire.services;

import com.rexxempire.data.models.Book;
import com.rexxempire.dtos.requests.BookRequest;
import com.rexxempire.dtos.responses.BookResponse;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookResponse addBook(String authorId, BookRequest bookRequest);
    List<Book> getAllBooks();
    Optional<Book> getBookById(String id);
    void deleteBookById(String id);
}
