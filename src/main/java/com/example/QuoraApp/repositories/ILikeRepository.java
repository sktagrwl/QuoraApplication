package com.example.QuoraApp.repositories;

import com.example.QuoraApp.models.Like;
import com.example.QuoraApp.models.TargetType;

import reactor.core.publisher.Mono;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ILikeRepository extends ReactiveMongoRepository<Like, String> {

    Mono<Long> countByTargetIdAndTargetType(String targetId, TargetType targetType);

    
}
