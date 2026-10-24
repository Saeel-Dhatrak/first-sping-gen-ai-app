package com.example.first_genAi_app.controller;

import com.example.first_genAi_app.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

    //ollama run llama3.2:3b

    //private final ChatService chatService;
    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/chat")
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

    // Below API can be tested with "http://localhost:8080/api/explain?topic=RAG&level=beginner"
    @GetMapping("/explain")
    public String explain(
            @RequestParam String topic,
            @RequestParam(defaultValue = "beginner") String level) {

        return chatClient
                .prompt()
                .system("""
                        You are a senior software developer and technical instructor.
                        Explain concepts clearly and use practical examples.
                        """)
                .user(user -> user
                        .text("""
                                Explain {topic} to a {level} developer.

                                Include:
                                1. A simple definition
                                2. Why it is used
                                3. A practical Java example
                                4. One common mistake
                                """)
                        .param("topic", topic)
                        .param("level", level))
                .call()
                .content();
    }
}
