package com.kafkaservice.kafka;

import com.kafkaservice.model.AuditLog;
import com.kafkaservice.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatConsumer {
    private final AuditLogRepository auditLogRepository;
    
    @KafkaListener(topics = "chat-messages", groupId = "notification-group")
    public void consumeMessage(String message) {
        AuditLog logEntry = AuditLog.builder()
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
        
        auditLogRepository.save(logEntry);
        log.info("✅ Saved to DB: {}", message);
    }
}
