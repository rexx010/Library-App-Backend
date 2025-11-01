package com.rexxempire.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Data
public class Book {
    private String id;
    private String title;
    private String authorName;
    private String bookCoverUrl;
    private String bio;
    private String email;
    private String isbn;
    private LocalDate publishedDate;
    private String genre;
    private int totalCopies;
    private int availableCopies;
    private Status status;

}
