package com.rexxempire.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
public class BookReservation {
    @Id
    private String id;
    @DBRef
    private User user;
    @DBRef
    private Book book;
    private LocalDate reservationDate;
    private ReservationStatus status;
}
