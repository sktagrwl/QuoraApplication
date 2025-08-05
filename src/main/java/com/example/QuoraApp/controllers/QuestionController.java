package com.example.QuoraApp.controllers;


import com.example.QuoraApp.dto.QuestionRequestDTO;
import com.example.QuoraApp.dto.QuestionResponseDTO;
import com.example.QuoraApp.services.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    final IQuestionService questionService;

    @PostMapping()
    public Mono<QuestionResponseDTO> createQuestion(@RequestBody QuestionRequestDTO questionRequestDTO){
        return questionService.createQuestion(questionRequestDTO)
                .doOnSuccess(response -> System.out.println("Question Created Successfully" + response))
                .doOnError(error -> System.out.println("Error Creating Question" + error));
    }

    @GetMapping("/search")
    public Flux<QuestionResponseDTO> searchQuestion(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int page){

        return questionService.searchQuestions(query,offset, page)
                .doOnComplete(() -> System.out.println("Questions has been searched"))
                .doOnError(error -> System.out.println("Error searching for questions" + error));
    }

    @GetMapping
    public Mono<PageImpl<QuestionResponseDTO>> getAllQuestions(
            @RequestParam(required = false) String cursor,
            @RequestParam(defaultValue = "10") int size){

        return questionService.getAllQuestion(cursor, size)
                .doOnSuccess(p -> System.out.println("All Questions has been fetched"))
                .doOnError(error -> System.out.println("Error searching for questions" + error));

    }



}
