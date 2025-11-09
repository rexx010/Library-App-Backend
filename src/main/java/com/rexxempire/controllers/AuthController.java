package com.rexxempire.controllers;


import com.rexxempire.data.models.User;
import com.rexxempire.dtos.requests.LoginRequest;
import com.rexxempire.dtos.requests.UserRequest;
import com.rexxempire.services.UserServiceImp;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private UserServiceImp userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.registerUser(userRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, HttpSession session) {
        User user = userService.login(loginRequest); // No longer Optional

        session.setAttribute("userId", user.getId());
        session.setAttribute("username", user.getUsername());
        session.setAttribute("role", user.getRole());

        Map<String, String> userMap = new HashMap<>();
        userMap.put("id", user.getId().toString());
        userMap.put("username", user.getUsername());
        userMap.put("role", user.getRole());

        return ResponseEntity.ok().header("Authorization", session.getId()).body(userMap);
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
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }
}
//@PostMapping("/login")
//public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpSession session) {
//    Optional<User> userOpt = userService.login(request);
//    if (userOpt.isEmpty()) {
//        return ResponseEntity.status(401).body("Invalid credentials");
//    }
//    User user = userOpt.get();
//    session.setAttribute("userId", user.getId());
//    session.setAttribute("role", user.getRole());
//    return ResponseEntity.ok("Login successful");
//}