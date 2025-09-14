package com.rexxempire.services;

import com.rexxempire.data.models.Book;
import com.rexxempire.data.models.BookReservation;
import com.rexxempire.data.models.ReservationStatus;
import com.rexxempire.data.models.User;
import com.rexxempire.data.repositories.BookRepository;
import com.rexxempire.data.repositories.BookReservationRepository;
import com.rexxempire.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookReservationService {
    @Autowired
    private BookReservationRepository bookReservationRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    public BookReservation reservation(String userId, String bookId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        BookReservation bookReservation = new BookReservation();
        bookReservation.setBook(book);
        bookReservation.setUser(user);
        bookReservation.setReservationDate(LocalDate.now());
        bookReservation.setStatus(ReservationStatus.ACTIVE);
        return bookReservationRepository.save(bookReservation);
    }

    public BookReservation deleteReservation(String userId, String bookId){
        BookReservation bookReservation = bookReservationRepository.findByUserIdAndBookIdAndStatus(userId, bookId, ReservationStatus.ACTIVE)
                .orElseThrow(() -> new IllegalArgumentException("No active reservation record found for this book and user"));
        bookReservation.setStatus(ReservationStatus.CANCELED);
        return bookReservationRepository.save(bookReservation);
    }
}
