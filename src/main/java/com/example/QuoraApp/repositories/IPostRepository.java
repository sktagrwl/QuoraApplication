package com.example.QuoraApp.repositories;


import com.example.QuoraApp.models.Post;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface IPostRepository extends ReactiveMongoRepository<Post, String> {

    Flux<Post> findByUserId(String userId);

}
