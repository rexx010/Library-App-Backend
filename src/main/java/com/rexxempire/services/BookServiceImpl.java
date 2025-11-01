package com.rexxempire.services;

import com.rexxempire.data.models.Author;
import com.rexxempire.data.models.Book;
import com.rexxempire.data.models.Status;
import com.rexxempire.data.repositories.AuthorRepository;
import com.rexxempire.data.repositories.BookRepository;
import com.rexxempire.dtos.requests.BookRequest;
import com.rexxempire.dtos.responses.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public BookResponse addBook(BookRequest bookRequest){
        Book book = new Book();
        book.setIsbn(bookRequest.getIsbn());
        book.setTitle(bookRequest.getTitle());
        book.setAuthorName(bookRequest.getAuthorName());
        book.setBookCoverUrl(bookRequest.getBookCoverUrl());
        book.setBio(bookRequest.getBio());
        book.setEmail(bookRequest.getEmail());
        book.setGenre(bookRequest.getGenre());
        book.setPublishedDate(bookRequest.getPublishedDate());
        book.setTotalCopies(bookRequest.getTotalCopies());
        book.setAvailableCopies(bookRequest.getTotalCopies());
        book.setStatus(Status.AVAILABLE);
        bookRepository.save(book);

        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setTitle(book.getTitle());
        bookResponse.setIsbn(book.getIsbn());
        bookResponse.setPublishedDate(book.getPublishedDate().toString());
        bookResponse.setAuthorName(book.getAuthorName());
        bookResponse.setBookCoverUrl(book.getBookCoverUrl());
        bookResponse.setBio(book.getBio());
        bookResponse.setEmail(book.getEmail());
        bookResponse.setGenre(book.getGenre());
        bookResponse.setStatus(book.getStatus());
        bookResponse.setTotalCopies(book.getTotalCopies());
        bookResponse.setAvailableCopies(book.getAvailableCopies());

        return bookResponse;
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(String id){
        return bookRepository.findById(id);
    }
    public void deleteBookById(String id){
        bookRepository.deleteById(id);
    }
}
