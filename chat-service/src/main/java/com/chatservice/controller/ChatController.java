package com.chatservice.controller;


import com.chatservice.manager.ChatManager;
import com.chatservice.model.ChatMessage;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/chat")
public class ChatController {
    private final ChatManager chatManager;

    @Autowired
    public ChatController(ChatManager chatManager){
        this.chatManager = chatManager;
    }
    
    @GetMapping("/messages/{senderId}/{receiverId}")
    public ResponseEntity<List<ChatMessage>> getMessages(@PathVariable("senderId") @NotNull Long senderId,
            @PathVariable("receiverId") @NotNull Long receiverId) {
        return ResponseEntity.ok(chatManager.getMessages(senderId, receiverId));
    }

    @PostMapping("/send")
    public ResponseEntity<ChatMessage> sendMessage(@RequestBody ChatMessage message) {
        return ResponseEntity.ok(chatManager.sendChatMessage(message));
    }
}
