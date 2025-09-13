package com.rexxempire.dtos.responses;

import com.rexxempire.data.models.Author;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookResponse {
    private String id;
    private String title;
    private String isbn;
    private String publishedDate;
    private Author authorName;
    private String genre;
    private int totalCopies;
    private int availableCopies;
}
