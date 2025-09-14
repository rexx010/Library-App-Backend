package com.rexxempire.data.repositories;

import com.rexxempire.data.models.BookReservation;
import com.rexxempire.data.models.ReservationStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookReservationRepository extends MongoRepository <BookReservation, String>{
    Optional<BookReservation> findByUserIdAndBookIdAndStatus(String userId, String bookId, ReservationStatus reservationStatus);
}
