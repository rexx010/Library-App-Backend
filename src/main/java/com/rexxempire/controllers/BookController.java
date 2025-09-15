package com.rexxempire.controllers;

import com.rexxempire.data.models.Book;
import com.rexxempire.dtos.requests.BookRequest;
import com.rexxempire.services.BookServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookServiceImpl bookService;

    @PostMapping("/{authorId}")
    public ResponseEntity<?> addBook(@Valid @RequestBody BookRequest bookRequest, @PathVariable("authorId") String authorId, HttpSession session){
        String role = (String) session.getAttribute("role");
        if(role == null || !role.equalsIgnoreCase("ADMIN")){
            return ResponseEntity.status(403).body("No access granted: Only ADMIN can add books");
        }
        return ResponseEntity.ok(bookService.addBook(authorId,bookRequest));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") String id){
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable("id") String id, HttpSession session){
        String role = (String) session.getAttribute("role");
        if(role == null || !role.equalsIgnoreCase("ADMIN")){
            return ResponseEntity.status(403).body("No access granted: Only ADMIN can delete books");
        }
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }
}
