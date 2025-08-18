package com.example.QuoraApp.adapters;

import com.example.QuoraApp.dto.AnswerRequestDTO;
import com.example.QuoraApp.dto.AnswerResponseDTO;
import com.example.QuoraApp.models.Answer;

public class AnswerAdapter {
    
    public static AnswerResponseDTO toAnswerResponseDTO(Answer answer) {
        return AnswerResponseDTO.builder()
                .id(answer.getId())
                .content(answer.getContent())
                .questionId(answer.getQuestionId())
                .createdAt(answer.getCreatedAt())
                .updatedAt(answer.getUpdatedAt())
                .build();
    }
    
    public static Answer toAnswer(AnswerRequestDTO answerRequestDTO) {
        return Answer.builder()
                .content(answerRequestDTO.getContent())
                .questionId(answerRequestDTO.getQuestionId())
                .build();
    }
}
