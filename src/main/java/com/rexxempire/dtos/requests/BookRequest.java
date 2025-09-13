package com.rexxempire.dtos.requests;

import com.rexxempire.data.models.Author;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;

@Data
public class BookRequest {
    private String title;
    private String isbn;
    private LocalDate publishedDate;
    private String genre;
    private int totalCopies;
    private int availableCopies;
}
