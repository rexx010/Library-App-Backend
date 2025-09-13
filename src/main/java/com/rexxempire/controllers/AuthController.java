package com.rexxempire.controllers;


import com.rexxempire.dtos.requests.UserRequest;
import com.rexxempire.services.UserServiceImp;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private UserServiceImp userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequest userRequest) {
        try {
            return ResponseEntity.ok(userService.registerUser(userRequest));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        if (auth.isAuthenticated()) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
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
