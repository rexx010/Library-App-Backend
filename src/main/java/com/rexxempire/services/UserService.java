package com.rexxempire.services;

import com.rexxempire.data.models.User;
import com.rexxempire.dtos.requests.UserRequest;
import com.rexxempire.dtos.responses.UserResponse;

public interface UserService {
    UserResponse registerUser(UserRequest userRequest);
    User validateUser(String email, String password);
}
