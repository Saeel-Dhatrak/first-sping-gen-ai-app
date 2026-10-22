package com.example.first_genAi_app.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String askGemini(String message){
        return chatClient
                .prompt()
                .user(message)
                .call()
                .content();
    }

}
