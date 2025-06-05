package com.example.demo.Entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Document(collection = "data")
@Data
@NoArgsConstructor
public class Entry {
    @Id
    private String id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;
}
