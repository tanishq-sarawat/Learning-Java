package com.example.demo.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Document(collection = "user")
@Data
@NoArgsConstructor
public abstract class User implements UserDetailsService {
    @Id
    private String id;
    @Indexed(unique = true)
    @NonNull
    private String userName;
    @NonNull
    private String Password ;
    @DBRef
    private ArrayList<Entry> entries = new ArrayList<>();
    private ArrayList<String> roles = new ArrayList<>();
}
