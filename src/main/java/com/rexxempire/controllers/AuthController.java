package com.rexxempire.controllers;

import com.rexxempire.data.models.User;
import com.rexxempire.dtos.requests.UserRequest;
import com.rexxempire.dtos.responses.UserResponse;
import com.rexxempire.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.registerUser(userRequest));
    }

    public ResponseEntity<?> validateUser(@RequestBody UserRequest userRequest, HttpSession session) {
        User user = userService.validateUser(userRequest.getEmail(), userRequest.getPassword());
        if(user == null){
            return ResponseEntity.status(401).body("Wrong Details");
        }
        session.setAttribute("userId", user.getId());
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setRole(user.getRole());
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getSessionUser(HttpSession session) {
        Object userId = session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).body("No user logged in");
        }
        return ResponseEntity.ok("Current logged in userId: " + userId.toString());
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out successfully");
    }
}
