package com.chatservice.manager;

import com.chatservice.clients.UserClient;
import com.chatservice.kafka.ChatProducer;
import com.chatservice.model.ChatMessage;
import com.chatservice.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatManager {
    private final ChatRepository chatRepository;
    private final ChatProducer chatProducer;
    private final UserClient userClient;
    
    @Autowired
    public ChatManager(ChatRepository chatRepository, ChatProducer chatProducer, UserClient userClient) {
        this.chatRepository = chatRepository;
        this.chatProducer = chatProducer;
        this.userClient = userClient;
    }
    
    public List<ChatMessage> getMessages(Long sender, Long receiver) {
        return chatRepository.findChatBetweenUsers(sender, receiver);
    }
    
    public ChatMessage sendChatMessage(ChatMessage chatMessage) {
        
        ChatMessage savedMessage = chatRepository.save(chatMessage);
        
        // Get usernames via Feign
        String sender = userClient.getUsernameById(savedMessage.getSenderId());
        String receiver = userClient.getUsernameById(savedMessage.getReceiverId());
        
        chatProducer.sendMessage(sender + " -> " + receiver + ": " + savedMessage.getContent());
        return savedMessage;
    }
}
