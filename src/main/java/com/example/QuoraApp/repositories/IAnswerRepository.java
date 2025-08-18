package com.example.QuoraApp.repositories;

import com.example.QuoraApp.models.Answer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface IAnswerRepository extends ReactiveMongoRepository<Answer, String> {
    
    /**
     * Find all answers by question ID
     */
    Flux<Answer> findByQuestionId(String questionId);
    
    /**
     * Find all answers by question ID ordered by creation date (newest first)
     */
    Flux<Answer> findByQuestionIdOrderByCreatedAtDesc(String questionId);
    
    /**
     * Count answers by question ID
     */
    Mono<Long> countByQuestionId(String questionId);
    
    /**
     * Delete all answers by question ID
     */
    Mono<Void> deleteByQuestionId(String questionId);
}
