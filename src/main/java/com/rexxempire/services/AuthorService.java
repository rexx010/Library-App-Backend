package com.rexxempire.services;

import com.rexxempire.data.models.Author;
import com.rexxempire.dtos.requests.AuthorRequest;
import com.rexxempire.dtos.responses.AuthorResponse;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    AuthorResponse createAuthorProfile(AuthorRequest authorRequest);
    List<Author> getAllAuthors();
    Optional<Author> getAuthorById(String id);
    void deleteAuthorById(String id);
    AuthorResponse updateAuthorById(String id, AuthorRequest authorRequest);
}
