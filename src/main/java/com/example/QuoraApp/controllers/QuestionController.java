package com.example.QuoraApp.controllers;


import com.example.QuoraApp.dto.DeleteResponseDTO;
import com.example.QuoraApp.dto.QuestionRequestDTO;
import com.example.QuoraApp.dto.QuestionResponseDTO;
import com.example.QuoraApp.models.QuestionElasticDocument;
import com.example.QuoraApp.services.IQuestionService;
import lombok.RequiredArgsConstructor;

import java.util.List;

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
                .doOnSuccess(result -> System.out.println("All Questions has been fetched"))
                .doOnError(error -> System.out.println("Error searching for questions" + error));

    }

    @GetMapping("/{id}")
    public Mono<QuestionResponseDTO> getQuestionById(@PathVariable String id){
        return questionService.getQuestionById(id)
                .doOnSuccess(result -> System.out.println("Fetched Question By id :" + id ))
                .doOnError(error -> System.out.println("Error searching for question by id" + error));
    }

    @DeleteMapping("/{id}")
    public Mono<DeleteResponseDTO> deleteQuestionById(@PathVariable String id) {
        return questionService.deleteQuestionById(id)
                .doOnSuccess(result -> System.out.println("Question with Id :" + id + " has been deleted"))
                .doOnError(error -> System.out.println("Error deleting question" + error));
    }

    @GetMapping("/tag/{tag}")
    public Flux<QuestionResponseDTO> getQuestionByTag(@PathVariable String tag){
        return questionService.getQuestionByTag(tag)
                .doOnComplete(() -> System.out.println("All Questions tagged as :" + tag +" are fetched"))
                .doOnError(error -> System.out.println("Error getting questions by tag" + error));
    }

    @GetMapping("/search/elasticsearch")
    public List<QuestionElasticDocument> searchQuestionsByElastic (@RequestParam String query){

        return questionService.searchQuestionsByElastic(query);

    }
}
