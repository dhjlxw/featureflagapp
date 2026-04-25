package com.example.demo.ai.beans.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Parameter {
    private String type;
    private String description;
    @JsonAlias("enum")
    private List<String> enumValues;

    public Parameter() {
    }

    public Parameter(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public Parameter(String type, String description, List<String> enumValues) {
        this.type = type;
        this.description = description;
        this.enumValues = enumValues;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getEnumValues() {
        return enumValues;
    }

    public void setEnumValues(List<String> enumValues) {
        this.enumValues = enumValues;
    }
}    
