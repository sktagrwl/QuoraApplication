package com.example.QuoraApp.repositories;

import com.example.QuoraApp.models.QuestionElasticDocument;

import reactor.core.publisher.Flux;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionDocumentRepository extends ReactiveElasticsearchRepository<QuestionElasticDocument, String> {

    Flux<QuestionElasticDocument> findByTitleContainingOrContentContainingOrTagsContaining(String title, String content, String tags);
    
}
