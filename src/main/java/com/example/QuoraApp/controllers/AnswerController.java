package com.example.QuoraApp.controllers;

import com.example.QuoraApp.dto.AnswerRequestDTO;
import com.example.QuoraApp.dto.AnswerResponseDTO;
import com.example.QuoraApp.services.IAnswerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/answers")
@RequiredArgsConstructor
public class AnswerController {

    private final IAnswerService answerService;

    /**
     * Create a new answer
     * POST /api/answers
     */
    @PostMapping
    public Mono<ResponseEntity<AnswerResponseDTO>> createAnswer(@Valid @RequestBody AnswerRequestDTO answerRequestDTO) {
        return answerService.createAnswer(answerRequestDTO)
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response))
                .doOnSuccess(response -> System.out.println("Answer creation endpoint called successfully"))
                .doOnError(error -> System.out.println("Error in answer creation endpoint: " + error.getMessage()));
    }

    /**
     * Get answer by ID
     * GET /api/answers/{id}
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<AnswerResponseDTO>> getAnswerById(@PathVariable String id) {
        return answerService.getAnswerById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .doOnSuccess(response -> System.out.println("Answer retrieval endpoint called for ID: " + id))
                .doOnError(error -> System.out.println("Error in answer retrieval endpoint for ID " + id + ": " + error.getMessage()));
    }

    /**
     * Get all answers for a specific question
     * GET /api/answers/question/{questionId}
     */
    @GetMapping("/question/{questionId}")
    public Flux<AnswerResponseDTO> getAnswersByQuestionId(@PathVariable String questionId) {
        return answerService.getAnswersByQuestionId(questionId)
                .doOnComplete(() -> System.out.println("Answers by question endpoint called for question ID: " + questionId))
                .doOnError(error -> System.out.println("Error in answers by question endpoint for question ID " + questionId + ": " + error.getMessage()));
    }

    /**
     * Get all answers
     * GET /api/answers
     */
    @GetMapping
    public Flux<AnswerResponseDTO> getAllAnswers() {
        return answerService.getAllAnswers()
                .doOnComplete(() -> System.out.println("All answers endpoint called"))
                .doOnError(error -> System.out.println("Error in all answers endpoint: " + error.getMessage()));
    }

    /**
     * Update an existing answer
     * PUT /api/answers/{id}
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<AnswerResponseDTO>> updateAnswer(
            @PathVariable String id,
            @Valid @RequestBody AnswerRequestDTO answerRequestDTO) {
        return answerService.updateAnswer(id, answerRequestDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .doOnSuccess(response -> System.out.println("Answer update endpoint called for ID: " + id))
                .doOnError(error -> System.out.println("Error in answer update endpoint for ID " + id + ": " + error.getMessage()));
    }

    /**
     * Delete an answer by ID
     * DELETE /api/answers/{id}
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteAnswer(@PathVariable String id) {
        return answerService.deleteAnswer(id)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .doOnSuccess(response -> System.out.println("Answer deletion endpoint called for ID: " + id))
                .doOnError(error -> System.out.println("Error in answer deletion endpoint for ID " + id + ": " + error.getMessage()));
    }

    /**
     * Delete all answers for a specific question
     * DELETE /api/answers/question/{questionId}
     */
    @DeleteMapping("/question/{questionId}")
    public Mono<ResponseEntity<Void>> deleteAnswersByQuestionId(@PathVariable String questionId) {
        return answerService.deleteAnswersByQuestionId(questionId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .doOnSuccess(response -> System.out.println("Answers deletion by question endpoint called for question ID: " + questionId))
                .doOnError(error -> System.out.println("Error in answers deletion by question endpoint for question ID " + questionId + ": " + error.getMessage()));
    }

    /**
     * Count answers for a specific question
     * GET /api/answers/count/question/{questionId}
     */
    @GetMapping("/count/question/{questionId}")
    public Mono<ResponseEntity<Long>> countAnswersByQuestionId(@PathVariable String questionId) {
        return answerService.countAnswersByQuestionId(questionId)
                .map(ResponseEntity::ok)
                .doOnSuccess(response -> System.out.println("Answer count endpoint called for question ID: " + questionId))
                .doOnError(error -> System.out.println("Error in answer count endpoint for question ID " + questionId + ": " + error.getMessage()));
    }

    /**
     * Health check endpoint
     * GET /api/answers/health
     */
    @GetMapping("/health")
    public Mono<ResponseEntity<String>> healthCheck() {
        return Mono.just(ResponseEntity.ok("Answer service is running"))
                .doOnSuccess(response -> System.out.println("Health check endpoint called"));
    }
}
