package com.rexxempire.controllers;

import com.rexxempire.data.models.BorrowBook;
import com.rexxempire.services.BorrowBookServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/borrow-books")
public class BorrowBookController {
    @Autowired
    private BorrowBookServiceImpl borrowBookService;

    @GetMapping("/{bookId}")
    public ResponseEntity<?> borrowBook(@PathVariable("bookId") String bookId, HttpSession session){
        String userId = (String) session.getAttribute("userId");
        if(userId == null){
            return ResponseEntity.status(401).body("No user logged in");
        }
        BorrowBook borrowBook = borrowBookService.borrowBook(userId, bookId);
        return ResponseEntity.ok(borrowBook);
    }

    @PostMapping("/borrowed")
    public ResponseEntity<Iterable<BorrowBook>> viewBorrowedBookByUserId(HttpSession session){
        String userId = (String) session.getAttribute("userId");
        if(userId == null){
            throw new RuntimeException("No user logged in");
        }
        return ResponseEntity.ok(borrowBookService.viewBorrowedBookByUserId(userId));
    }
    
    @PostMapping("/{bookId}")
    public ResponseEntity<BorrowBook> returnBook(@PathVariable("bookId") String bookId, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).build();
        }
        BorrowBook record = borrowBookService.returnBook(userId, bookId);
        return ResponseEntity.ok(record);
    }
}