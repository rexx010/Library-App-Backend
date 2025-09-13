package com.rexxempire.controllers;

import com.rexxempire.data.models.Book;
import com.rexxempire.dtos.requests.BookRequest;
import com.rexxempire.dtos.responses.BookResponse;
import com.rexxempire.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/{authorId}")
    public ResponseEntity<BookResponse> addBook(@RequestBody BookRequest bookRequest, @PathVariable String authorId){
        return ResponseEntity.ok(bookService.addBook(authorId,bookRequest));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id){
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable String id){
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }
}
