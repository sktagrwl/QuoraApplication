package com.example.QuoraApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnswerResponseDTO {

    private String id;

    private String content;

    private String questionId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
