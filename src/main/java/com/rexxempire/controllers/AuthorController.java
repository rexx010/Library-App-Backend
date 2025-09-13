package com.rexxempire.controllers;

import com.rexxempire.data.models.Author;
import com.rexxempire.dtos.requests.AuthorRequest;
import com.rexxempire.dtos.responses.AuthorResponse;
import com.rexxempire.services.AuthorService;
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
    public AuthorResponse createAuthorProfile(@RequestBody AuthorRequest authorRequest){
        return ResponseEntity.ok(authorService.createAuthorProfile(authorRequest)).getBody();
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors(){
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable String id){
        return authorService.getAuthorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthorById(@PathVariable String id){
        authorService.deleteAuthorById(id);
        return ResponseEntity.noContent().build();
    }
}