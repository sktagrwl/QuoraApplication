package com.example.QuoraApp.models;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "questions")
public class Question {

    @Id
    private String id;

    @NotBlank(message ="Title is required")
    @Size(min = 10, max =100, message ="Title mush be between 10 and 100 characters")
    private String title;

    @NotBlank(message ="Content is required")
    @Size(min = 10, max =1000, message ="Content mush be between 10 and 1000 characters")
    private String content;

    @NotEmpty(message ="Add at least one tag")
    @Size(min = 1, max = 10, message ="Maximum 10 tags are allowed")
    private List<@NotBlank(message = "Tags cannot be blank") String> tags;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
