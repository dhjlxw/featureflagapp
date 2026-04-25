package com.example.demo.ai.beans.request;

import java.util.List;

public class OpenAIToolChoiceRequest {
    private String model;
    private List<Message> messages;
    private List<FunctionTool> tools;
    private String toolChoice;

    public OpenAIToolChoiceRequest() {
    }

    public OpenAIToolChoiceRequest(String model, List<Message> messages, List<FunctionTool> tools, String toolChoice) {
        this.model = model;
        this.messages = messages;
        this.tools = tools;
        this.toolChoice = toolChoice;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<FunctionTool> getTools() {
        return tools;
    }

    public void setTools(List<FunctionTool> tools) {
        this.tools = tools;
    }

    public String getToolChoice() {
        return toolChoice;
    }

    public void setToolChoice(String toolChoice) {
        this.toolChoice = toolChoice;
    }
}
