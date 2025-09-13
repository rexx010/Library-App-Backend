package com.rexxempire.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Author {
    @Id
    private String id;
    private String name;
    private String bio;
    private String email;
}
