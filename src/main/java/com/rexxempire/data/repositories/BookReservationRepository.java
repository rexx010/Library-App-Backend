package com.rexxempire.data.repositories;

import com.rexxempire.data.models.BookReservation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookReservationRepository extends MongoRepository <BookReservation, String>{
}
