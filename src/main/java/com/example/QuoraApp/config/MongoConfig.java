package com.example.QuoraApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ReactiveBeforeConvertCallback;
import com.example.QuoraApp.models.BaseModel;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Configuration
public class MongoConfig {

    @Bean
    public ReactiveBeforeConvertCallback<BaseModel> beforeConvertCallback() {
        return (entity, collection) -> {
            if (entity.getCreatedAt() == null) {
                entity.setCreatedAt(LocalDateTime.now());
            }
            entity.setUpdatedAt(LocalDateTime.now());
            return Mono.just(entity);
        };
    }
}
