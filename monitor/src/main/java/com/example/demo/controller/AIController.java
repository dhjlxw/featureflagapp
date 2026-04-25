package com.example.demo.controller;

import com.example.demo.ai.MyAI;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AIController {

    @PostMapping("/api/ai/question")
    public Map<String, String> askQuestion(@RequestBody Map<String, String> request) {
        Map<String, String> response = new HashMap<>();
        try {
            String question = request.get("question");
            String answer = MyAI.invoke(question);
            response.put("answer", answer);
        } catch (Exception e) {
            response.put("error", e.getMessage());
        }
        return response;
    }
}
