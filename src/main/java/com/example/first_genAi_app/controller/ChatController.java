package com.example.first_genAi_app.controller;

import com.example.first_genAi_app.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    //ollama run llama3.2:3b

    //private final ChatService chatService;
    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping
    public String chat(@RequestParam String message){
        return chatClient
                .prompt()
                .system("""
                        You are a senior Java and Spring Boot developer.
                        Give clear, practical and technically accurate answers.
                        When explaining code, prefer modern Java and Spring Boot practices.
                        Keep answers concise unless the user asks for detailed explanations.
                        """)
                .user(message)
                .call()
                .content();
    }
}
