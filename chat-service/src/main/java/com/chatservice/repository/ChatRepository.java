package com.chatservice.repository;

import com.chatservice.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ChatRepository extends JpaRepository<ChatMessage, UUID> {
    
    @Query(value = "SELECT * FROM chat_message cm WHERE " +
            "(cm.sender_id = :user1 AND cm.receiver_id = :user2) OR " +
            "(cm.sender_id = :user2 AND cm.receiver_id = :user1) " +
            "ORDER BY cm.timestamp DESC", nativeQuery = true)
    List<ChatMessage> findChatBetweenUsers(@Param("user1") Long user1, @Param("user2") Long user2);
    
    List<ChatMessage> findBySenderIdAndReceiverIdOrReceiverIdAndSenderId(Long sender1, Long receiver1,
                             Long sender2, Long receiver2);
}
