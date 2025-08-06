package com.example.QuoraApp.adapters;


import com.example.QuoraApp.dto.QuestionResponseDTO;
import com.example.QuoraApp.models.Question;
import org.springframework.web.bind.annotation.GetMapping;

public class QuestionAdapter {
    public static QuestionResponseDTO toQuestionResponseDTO(Question question){
        return QuestionResponseDTO.builder()
                .id(question.getId())
                .title(question.getTitle())
                .tags(question.getTags())
                .content(question.getContent())
                .createdAt(question.getCreatedAt())
                .build();
    }
}
