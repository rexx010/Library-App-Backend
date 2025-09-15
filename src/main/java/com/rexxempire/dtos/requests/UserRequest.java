package com.rexxempire.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank(message = "Username must not be null or empty")
    private String username;
    @NotBlank(message = "Email must not be null or empty")
    @Pattern(regexp = "^[A-Za-z0-9]+@[A-Za-z]+\\.com$", message = "Invalid email address")
    private String email;
    @NotBlank(message = "Password must not be null or empty")
    private String password;
    @NotBlank(message = "Role must not be null or empty")
    private String role;
}
