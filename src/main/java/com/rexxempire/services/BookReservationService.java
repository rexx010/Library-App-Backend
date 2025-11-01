package com.rexxempire.services;

import com.rexxempire.data.models.BookReservation;

import java.util.List;

public interface BookReservationService {
    BookReservation reservation(String userId, String bookId);
    BookReservation deleteReservation(String userId, String bookId);
    List<BookReservation> getReservationsByUser(String userId);
}
