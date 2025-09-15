package com.rexxempire.services;

import com.rexxempire.data.models.Author;
import com.rexxempire.data.repositories.AuthorRepository;
import com.rexxempire.dtos.requests.AuthorRequest;
import com.rexxempire.dtos.responses.AuthorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService{
    @Autowired
    private AuthorRepository authorRepository;

    public AuthorResponse createAuthorProfile(AuthorRequest authorRequest){
        Author author = new Author();
        author.setName(authorRequest.getName());
        author.setBio(authorRequest.getBio());
        author.setEmail(authorRequest.getEmail());
        authorRepository.save(author);

        AuthorResponse authorResponse = new AuthorResponse();
        authorResponse.setId(author.getId());
        authorResponse.setName(author.getName());
        authorResponse.setBio(author.getBio());
        authorResponse.setEmail(author.getEmail());
        return authorResponse;
    }

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(String id){
        return authorRepository.findById(id);
    }

    public void deleteAuthorById(String id){
        authorRepository.deleteById(id);
    }

    public AuthorResponse updateAuthorById(String id, AuthorRequest authorRequest){
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id : " + id));
        author.setName(authorRequest.getName());
        author.setBio(authorRequest.getBio());
        author.setEmail(authorRequest.getEmail());

        authorRepository.save(author);

        Author updatedAuthor = authorRepository.save(author);
        AuthorResponse response = new AuthorResponse();
        response.setId(updatedAuthor.getId());
        response.setName(updatedAuthor.getName());
        response.setEmail(updatedAuthor.getEmail());
        response.setBio(updatedAuthor.getBio());

        return response;
    }
}
