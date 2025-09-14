package com.rexxempire.controllers;

import com.rexxempire.data.models.BorrowBook;
import com.rexxempire.services.BorrowBookService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/borrow-books")
public class BorrowBookController {
    @Autowired
    private BorrowBookService borrowBookService;

    @RequestMapping("/{bookId}")
    public ResponseEntity<?> borrowBook(@PathVariable String bookId, HttpSession session){
        String userId = (String) session.getAttribute("userId");
        if(userId == null){
            return ResponseEntity.status(401).body("No user logged in");
        }
        BorrowBook borrowBook = borrowBookService.borrowBook(userId, bookId);
        return ResponseEntity.ok(borrowBook);
    }

    @PostMapping("/return/{bookId}")
    public ResponseEntity<BorrowBook> returnBook(@PathVariable String bookId, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).build(); // not logged in
        }
        BorrowBook record = borrowBookService.returnBook(userId, bookId);
        return ResponseEntity.ok(record);
    }
}