package com.chatservice.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    public static final String TOPIC = "chat-messages";

    public void sendMessage(String messages){
        kafkaTemplate.send(TOPIC, messages);
    }
}
