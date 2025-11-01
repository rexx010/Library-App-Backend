package com.rexxempire.dtos.responses;

import com.rexxempire.data.models.Author;
import com.rexxempire.data.models.Status;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookResponse {
    private String id;
    private String title;
    private String isbn;
    private String publishedDate;
    private String authorName;
    private String bookCoverUrl;
    private Status status;
    private String bio;
    private String email;
    private String genre;
    private int totalCopies;
    private int availableCopies;
}
