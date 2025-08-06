package com.example.QuoraApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionRequestDTO {


    @NotBlank(message ="Title is required")
    @Size(min = 10, max =100, message ="Title mush be between 10 and 100 characters")
    private String title;

    @NotBlank(message ="Content is required")
    @Size(min = 10, max =1000, message ="Content mush be between 10 and 1000 characters")
    private String content;

    @NotEmpty(message = "Add at least one tag")
    @Size(max = 10, message = "Maximum 10 tags are allowed")
    private List<@NotBlank(message = "Tags cannot be blank") String> tags;

}
