package com.rexxempire.services;

import com.rexxempire.data.models.User;
import com.rexxempire.data.repositories.UserRepository;
import com.rexxempire.dtos.requests.UserRequest;
import com.rexxempire.dtos.responses.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceImpTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImp userService;

    @BeforeEach
    public void setUp() {
       userRepository.deleteAll();
    }

    @Test
    public void registerUser() {
        UserRequest userRequest = new UserRequest();
        userService.registerUser(userRequest);
        assertEquals(1,userRepository.count());
    }

    @Test
    public void registerOneUser_UserCountIsOne(){
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("dami@gmail.com");
        userRequest.setPassword("09876");
        userRequest.setUsername("dami");
        userRequest.setRole("ADMIN");
        userService.registerUser(userRequest);
        assertEquals(1,userRepository.count());
    }
    @Test
    public void registerTwoUser_CountReturnTwo(){
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("dami@gmail.com");
        userRequest.setPassword("09876");
        userRequest.setUsername("dami");
        userRequest.setRole("ADMIN");
        userService.registerUser(userRequest);
        assertEquals(1,userRepository.count());

        userRequest.setEmail("Tobi@gmail.com");
        userRequest.setPassword("12345");
        userRequest.setUsername("tobi");
        userRequest.setRole("User");
        userService.registerUser(userRequest);
        assertEquals(2,userRepository.count());
    }

    @Test
    public void validateUser_UserExist_ReturnUser(){
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("dami@gmail.com");
        userRequest.setPassword("09876");
        userRequest.setUsername("dami");
        userRequest.setRole("ADMIN");
        UserResponse response = userService.registerUser(userRequest);
        assertEquals("dami", response.getUsername());
        assertEquals("dami@gmail.com", response.getEmail());
        assertEquals("ADMIN", response.getRole());
    }

    @Test
    public void addUser_userCantLoginWithTheSameEmail(){
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("dami@gmail.com");
        userRequest.setPassword("09876");
        userRequest.setUsername("dami");
        userRequest.setRole("ADMIN");
        userService.registerUser(userRequest);
        assertEquals(1,userRepository.count());

        UserRequest userRequest2 = new UserRequest();
        userRequest2.setEmail("dami@gmail.com");
        userRequest2.setPassword("1234");
        userRequest2.setUsername("tobi");
        userRequest2.setRole("USER");
        assertThrows(RuntimeException.class, () -> userService.registerUser(userRequest2));
    }
}