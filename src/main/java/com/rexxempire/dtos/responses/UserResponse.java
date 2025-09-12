package com.rexxempire.dtos.responses;

import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String username;
    private String email;
    private String password;
    private String role;
}
