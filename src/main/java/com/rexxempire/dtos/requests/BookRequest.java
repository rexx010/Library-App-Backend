package com.rexxempire.dtos.requests;

import com.rexxempire.data.models.Author;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;

@Data
public class BookRequest {
    @NotBlank(message = "Title must not be null or empty")
    private String title;
    @NotBlank(message = "ISBN must not be null or empty")
    private String isbn;
    private LocalDate publishedDate;
    @NotBlank(message = "Author must not be null or empty")
    private String genre;
    @NotNull(message = "Total copies is required")
    @Min(value = 1, message = "Total copies must be at least 1")
    @Positive(message = "Total copies must be a positive number")
    private int totalCopies;
    @Positive(message = "Total copies must be a positive number")
    private int availableCopies;
}
