package com.example.QuoraApp.producer;

import com.example.QuoraApp.config.KafkaConfig;
import com.example.QuoraApp.events.ViewCountEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishViewCountEvent(ViewCountEvent viewCountEvent) {
        kafkaTemplate.send(KafkaConfig.TOPIC_NAME, viewCountEvent.getTargetId(), viewCountEvent)
                .whenComplete((result, err) -> {
                    if(err != null) {
                        System.out.println("Error publishing view count event: " + err.getMessage());
                    }
                });
    }
}

// notification to send an email whenever a question is liked
// may use redis queue as well