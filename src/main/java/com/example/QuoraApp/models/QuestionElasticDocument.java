package com.example.QuoraApp.models;


import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "questions")
public class QuestionElasticDocument {

    @Id
    private String id;

    private String title;

    private String content;

    public List<String> tags;

}
