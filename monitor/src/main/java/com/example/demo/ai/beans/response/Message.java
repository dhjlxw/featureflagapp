package com.example.demo.ai.beans.response;

import java.util.List;

public class Message {
    private String role;
    private Object reasoning_content;
    private String content;
    private List<Object> tool_calls;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Object getReasoning_content() {
        return reasoning_content;
    }

    public void setReasoning_content(Object reasoning_content) {
        this.reasoning_content = reasoning_content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Object> getTool_calls() {
        return tool_calls;
    }

    public void setTool_calls(List<Object> tool_calls) {
        this.tool_calls = tool_calls;
    }
}
