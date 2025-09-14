package com.rexxempire.services;

import com.rexxempire.data.models.Book;
import com.rexxempire.data.models.BorrowBook;
import com.rexxempire.data.models.Status;
import com.rexxempire.data.models.User;
import com.rexxempire.data.repositories.BorrowBookRepository;
import com.rexxempire.data.repositories.BookRepository;
import com.rexxempire.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class BorrowBookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BorrowBookRepository borrowBookRepository;


    public BorrowBook borrowBook(String userId, String bookId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        if(book.getAvailableCopies() <= 0){
            throw new RuntimeException("Book is not available");
        }
        BorrowBook borrowBook = new BorrowBook();
        borrowBook.setUser(user);
        borrowBook.setBook(book);
        borrowBook.setLoanDate(LocalDateTime.now());
        borrowBook.setReturnDate(LocalDate.now().plusMonths(1));
        borrowBook.setStatus(Status.CHECKED_OUT);

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);
        return borrowBookRepository.save(borrowBook);
    }


    public BorrowBook returnBook(String userId, String bookId){
        BorrowBook borrowBook = borrowBookRepository.findByUserIdAndBookIdAndStatus(userId, bookId, Status.CHECKED_OUT)
                .orElseThrow(() -> new RuntimeException("Book is not checked out"));
        borrowBook.setStatus(Status.RETURNED);
        borrowBook.setReturnDate(LocalDate.now());

        Book book = borrowBook.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        return borrowBookRepository.save(borrowBook);
    }
}
