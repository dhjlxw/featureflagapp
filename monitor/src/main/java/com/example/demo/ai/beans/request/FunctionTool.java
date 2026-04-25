package com.example.demo.ai.beans.request;

import java.util.List;
import java.util.Map;

// 表示一个工具
public class FunctionTool {
    private String type;
    private ToolFunction function;

    public FunctionTool() {
    }

    public FunctionTool(String type, ToolFunction function) {
        this.type = type;
        this.function = function;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ToolFunction getFunction() {
        return function;
    }

    public void setFunction(ToolFunction function) {
        this.function = function;
    }
}

// 表示工具的函数信息
// 表示工具函数的参数信息

