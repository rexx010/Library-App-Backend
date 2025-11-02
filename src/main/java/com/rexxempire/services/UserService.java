package com.rexxempire.services;

import com.rexxempire.data.models.User;
import com.rexxempire.dtos.requests.LoginRequest;
import com.rexxempire.dtos.requests.UserRequest;
import com.rexxempire.dtos.responses.UserResponse;

import java.util.Optional;

public interface UserService {
    UserResponse registerUser(UserRequest userRequest);
    User login(LoginRequest loginRequest);
    Optional<User> findById(String id);
}