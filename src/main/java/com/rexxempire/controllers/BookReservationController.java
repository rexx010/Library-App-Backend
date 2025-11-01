package com.rexxempire.controllers;

import com.rexxempire.services.BookReservationServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book-reservations")
public class BookReservationController {
    @Autowired
    private BookReservationServiceImpl bookReservationService;

    @GetMapping("/reserved")
    private ResponseEntity<?> getAllBookReservations(HttpSession session){
        String userId = (String) session.getAttribute("userId");
        if(userId == null){
            throw new RuntimeException("No user logged in");
        }
        return ResponseEntity.ok(bookReservationService.getReservationsByUser(userId));
    }

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
