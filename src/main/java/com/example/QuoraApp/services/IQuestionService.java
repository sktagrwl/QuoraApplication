package com.example.QuoraApp.services;

import com.example.QuoraApp.dto.DeleteResponseDTO;
import com.example.QuoraApp.dto.QuestionRequestDTO;
import com.example.QuoraApp.dto.QuestionResponseDTO;
import org.springframework.data.domain.PageImpl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IQuestionService {

    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO);

    public Flux<QuestionResponseDTO> searchQuestions(String searchTerm, int offset, int page);

    public Mono<PageImpl<QuestionResponseDTO>> getAllQuestion(String cursor, int size);

    public Mono<QuestionResponseDTO> getQuestionById(String id);

    public Mono<DeleteResponseDTO> deleteQuestionById(String id);

}
