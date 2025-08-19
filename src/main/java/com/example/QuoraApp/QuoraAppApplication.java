package com.example.QuoraApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
@EnableReactiveElasticsearchRepositories
public class QuoraAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuoraAppApplication.class, args);
	}

}
