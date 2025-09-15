package com.rexxempire.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Username must not be null or empty")
    private  String username;
    @NotBlank(message = "Password must not be null or empty")
    private  String password;
}
