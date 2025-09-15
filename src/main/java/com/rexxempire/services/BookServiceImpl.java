package com.rexxempire.services;

import com.rexxempire.data.models.Author;
import com.rexxempire.data.models.Book;
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

    public BookResponse addBook(String authorId, BookRequest bookRequest){
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found with id : " + authorId));
        Book book = new Book();
        book.setIsbn(bookRequest.getIsbn());
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(author);
        book.setGenre(bookRequest.getGenre());
        book.setPublishedDate(bookRequest.getPublishedDate());
        book.setTotalCopies(bookRequest.getTotalCopies());
        book.setAvailableCopies(bookRequest.getAvailableCopies());
        bookRepository.save(book);

        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setTitle(book.getTitle());
        bookResponse.setIsbn(book.getIsbn());
        bookResponse.setPublishedDate(book.getPublishedDate().toString());
        bookResponse.setAuthorName(book.getAuthor());
        bookResponse.setGenre(book.getGenre());
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
