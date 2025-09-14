package com.rexxempire.controllers;


import com.rexxempire.dtos.requests.LoginRequest;
import com.rexxempire.dtos.requests.UserRequest;
import com.rexxempire.services.UserServiceImp;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private UserServiceImp userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequest userRequest) {
        try {
            return ResponseEntity.ok(userService.registerUser(userRequest));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        return userService.login(loginRequest)
                .map(user -> {
                    session.setAttribute("userId", user.getId());
                    session.setAttribute("role", user.getRole());
                    return ResponseEntity.ok("Login successful");
                })
                .orElse(ResponseEntity.status(401).body("Invalid credentials"));
    }


    @GetMapping("/me")
    public ResponseEntity<?> me(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).body("Not logged in");
        }
        return ResponseEntity.ok("Logged in as userId: " + userId +
                ", role: " + session.getAttribute("role"));
    }



    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out successfully");
    }
}
//    public ResponseEntity<?> validateUser(@RequestBody UserRequest userRequest, HttpSession session) {
//        User user = userService.validateUser(userRequest.getEmail(), userRequest.getPassword());
//        if(user == null){
//            return ResponseEntity.status(401).body("Wrong Details");
//        }
//        session.setAttribute("userId", user.getId());
//        UserResponse userResponse = new UserResponse();
//        userResponse.setId(user.getId());
//        userResponse.setUsername(user.getUsername());
//        userResponse.setEmail(user.getEmail());
//        userResponse.setRole(user.getRole());
//        return ResponseEntity.ok(userResponse);
//    }
