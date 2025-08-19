package com.example.QuoraApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnswerRequestDTO {

    @NotBlank(message ="Content is required")
    @Size(min = 10, max =1000, message ="Content mush be between 10 and 1000 characters")
    private String content;

    @NotBlank(message = "Question ID is required")
    private String questionId;
}
