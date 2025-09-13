package com.rexxempire.dtos.responses;

import lombok.Data;

@Data
public class AuthorResponse {
    private String id;
    private String name;
    private String bio;
    private String email;
}
