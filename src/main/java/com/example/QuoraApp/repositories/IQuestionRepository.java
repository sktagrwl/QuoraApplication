package com.example.QuoraApp.repositories;

import com.example.QuoraApp.models.Question;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;


@Repository
public interface IQuestionRepository extends ReactiveMongoRepository<Question, String>{

    @Query("{'$or' : [{'title' : {$regex: ?0, $options: 'i'} }, {'content': {$regex: ?0, $options: 'i'} }]}")
    Flux<Question> findByTitleOrContentContainingIgnoreCase(String searchTerm, Pageable pageable);

    Flux<Question> findByCreatedAtGreaterThanOrderByCreatedAtAsc(LocalDateTime cursor, Pageable pageable);

    Flux<Question> findTop10ByOrderByCreatedAt();


}
