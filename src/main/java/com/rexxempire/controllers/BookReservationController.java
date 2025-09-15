package com.rexxempire.controllers;

import com.rexxempire.services.BookReservationServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book-reservations")
public class BookReservationController {
    @Autowired
    private BookReservationServiceImpl bookReservationService;

    @PostMapping("/{bookId}")
    public ResponseEntity<?> reservation(@PathVariable("bookId") String bookId, HttpSession session){
        String userId = (String) session.getAttribute("userId");
        if(userId == null){
            return ResponseEntity.status(401).body("No user found");
        }
        return ResponseEntity.ok(bookReservationService.reservation(userId, bookId));
    }

    @PostMapping("/cancel/{bookId}")
    public ResponseEntity<?> cancelReservation(@PathVariable("bookId") String bookId, HttpSession session){
        String userId = (String) session.getAttribute("userId");
        if(userId == null){
            return ResponseEntity.status(401).body("No user found");
        }
        return ResponseEntity.ok(bookReservationService.deleteReservation(userId, bookId));
    }
}
