package com.rexxempire.dtos.requests;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class AuthorRequest {
    private String name;
    private String bio;
    private String email;
}
