package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.ai.DeepSeek;

import java.util.Map;

@RestController
@RequestMapping("/api/interview-resume")
public class InterviewResumeController {

    @PostMapping("/match")
    public Map<String, String> matchResume(@RequestBody Map<String, String> request) throws Exception{
        String interview = request.get("interview");
        String resume = request.get("resume");
        String question=String.format("请认真阅读职位要求（在<职位要求></职位要求>中间的部分)和个人简历（在<个人简历></个人简历>中间的部分)，写出一份匹配列表，用于向该职位公司展示该用户的简历如何匹配该职位。<职位要求>%s</职位要求>.<用户简历>%s</用户简历>", interview,resume);
        String answer=DeepSeek.invoke(question);
        // Here you would implement your matching logic
        String result =answer;
        
        return Map.of("result", result);
    }
}
