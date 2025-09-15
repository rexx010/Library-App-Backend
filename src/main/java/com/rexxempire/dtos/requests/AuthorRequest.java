package com.rexxempire.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

@Data
public class AuthorRequest {
    @NotBlank(message = "Name must not be null or empty")
    private String name;
    @NotBlank(message = "Bio must not be null or empty")
    private String bio;
    @NotBlank(message = "Email must not be null or empty")
    @Pattern(regexp = "^[A-Za-z0-9]+@[A-Za-z]+\\.com$", message = "Invalid email address")
    private String email;
}
