package com.rexxempire.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document
@Data
public class BorrowBook {
    @Id
    private String id;
    @DBRef
    private User user;
    @DBRef
    private Book book;
    private LocalDateTime loanDate;
    private LocalDate returnDate;
    private Status status;
}
