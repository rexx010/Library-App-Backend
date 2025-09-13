package com.rexxempire.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
public class BookLoan {
    @Id
    private String id;
    private String userId;
    private String bookId;
    private LocalDateTime loanDate;
    private LocalDateTime returnDate;
    private Status status;
}
