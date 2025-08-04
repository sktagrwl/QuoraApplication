package com.example.QuoraApp.services;

import com.example.QuoraApp.adapters.QuestionAdapter;
import com.example.QuoraApp.dto.QuestionRequestDTO;
import com.example.QuoraApp.dto.QuestionResponseDTO;
import com.example.QuoraApp.models.Question;
import com.example.QuoraApp.repositories.IQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService{

    private final IQuestionRepository questionRepository;

    @Override
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO) {

        Question question = Question.builder()
                .title(questionRequestDTO.getTitle())
                .content(questionRequestDTO.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return questionRepository.save(question)
                .map(QuestionAdapter::toQuestionResponseDTO)
                .doOnSuccess(response -> System.out.println("Question has been created" + response))
                .doOnError(error -> System.out.println("Error creating question" + error));
    }

//    @Override
//    public Flux<QuestionResponseDTO> searchQuestions(String searchTerm, int offset, int page){
//        return null;
////        return questionRepository.findByTitleOrContentContainingIgnoreCase(searchTerm, PageRequest.of(offset, page));
//    }
}
