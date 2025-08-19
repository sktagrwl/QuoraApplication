package com.example.QuoraApp.services;

import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.stereotype.Service;

import com.example.QuoraApp.models.Question;
import com.example.QuoraApp.models.QuestionElasticDocument;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class QuestionIndexService implements IQuestionIndexService{



    private final ReactiveElasticsearchOperations elasticsearchOperations;

    @Override
    public Mono<Void> createQuestionIndex(Question question) {
        QuestionElasticDocument document = QuestionElasticDocument.builder()
        .id(question.getId())
        .title(question.getTitle())
        .content(question.getContent())
        .tags(question.getTags())
        .build();

        return elasticsearchOperations.save(document)
        .doOnSuccess(saved -> System.out.println("Successfully saved document"))
        .doOnError(error -> System.out.println("Error saving document : " + error))
        .then();

    }
    
}
