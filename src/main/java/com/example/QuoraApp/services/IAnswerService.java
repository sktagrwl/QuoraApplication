package com.example.QuoraApp.services;

import com.example.QuoraApp.dto.AnswerRequestDTO;
import com.example.QuoraApp.dto.AnswerResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAnswerService {

    /**
     * Create a new answer
     */
    Mono<AnswerResponseDTO> createAnswer(AnswerRequestDTO answerRequestDTO);

    /**
     * Get answer by ID
     */
    Mono<AnswerResponseDTO> getAnswerById(String id);

    /**
     * Get all answers for a specific question
     */
    Flux<AnswerResponseDTO> getAnswersByQuestionId(String questionId);

    /**
     * Get all answers
     */
    Flux<AnswerResponseDTO> getAllAnswers();

    /**
     * Update an existing answer
     */
    Mono<AnswerResponseDTO> updateAnswer(String id, AnswerRequestDTO answerRequestDTO);

    /**
     * Delete an answer by ID
     */
    Mono<Void> deleteAnswer(String id);

    /**
     * Delete all answers for a specific question
     */
    Mono<Void> deleteAnswersByQuestionId(String questionId);

    /**
     * Count answers for a specific question
     */
    Mono<Long> countAnswersByQuestionId(String questionId);
}
