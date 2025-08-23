package com.example.QuoraApp.repositories;

import com.example.QuoraApp.models.Answer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface IAnswerRepository extends ReactiveMongoRepository<Answer, String> {
    

    Flux<Answer> findByQuestionId(String questionId);

    Flux<Answer> findByQuestionIdOrderByCreatedAtDesc(String questionId);

    Mono<Long> countByQuestionId(String questionId);

    Mono<Void> deleteByQuestionId(String questionId);
}
