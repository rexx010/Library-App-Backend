package com.rexxempire.dtos.requests;

import com.rexxempire.data.models.Book;
import com.rexxempire.data.models.Status;
import com.rexxempire.data.models.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BorrowBookRequest {
    private User user;
    private Book book;
    private LocalDateTime loanDate;
    private LocalDateTime returnDate;
    private Status status;
}
