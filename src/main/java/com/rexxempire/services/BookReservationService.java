package com.rexxempire.services;

import com.rexxempire.data.models.BookReservation;

public interface BookReservationService {
    BookReservation reservation(String userId, String bookId);
    BookReservation deleteReservation(String userId, String bookId);
}
