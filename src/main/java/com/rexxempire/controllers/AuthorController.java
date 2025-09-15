package com.rexxempire.controllers;

import com.rexxempire.data.models.Author;
import com.rexxempire.dtos.requests.AuthorRequest;
import com.rexxempire.dtos.responses.AuthorResponse;
import com.rexxempire.services.AuthorService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<?> createAuthorProfile(@Valid @RequestBody AuthorRequest authorRequest, HttpSession session){
        String role = (String) session.getAttribute("role");
        if(role == null || !role.equals("ADMIN")){
            return ResponseEntity.status(403).body("Access not granted: Only admins can create authors");
        }
        return ResponseEntity.ok(authorService.createAuthorProfile(authorRequest));
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors(){
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable("id") String id){
        return authorService.getAuthorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthorById(@PathVariable("id") String id, HttpSession session){
        String role = (String) session.getAttribute("role");
        if(role == null || !role.equalsIgnoreCase("ADMIN")){
            return ResponseEntity.status(403).body("Forbidden: Only admins can update authors");
        }
        authorService.deleteAuthorById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthorById(@PathVariable("id") String id, @Valid @RequestBody AuthorRequest authorRequest, HttpSession session){
        String role = (String) session.getAttribute("role");
        if(role == null || !role.equalsIgnoreCase("ADMIN")){
            return ResponseEntity.status(403).body("Forbidden: Only admins can update authors");
        }
        return ResponseEntity.ok(authorService.updateAuthorById(id, authorRequest));
    }
}