package com.example.demo.ai.beans.request;

import java.util.List;
import java.util.Map;

public class ToolFunction {
    private String name;
    private String description;
    private Map<String, Parameter> parameters;
    private List<String> required;
    private Parameter returnParameter;

    public ToolFunction() {
    }

    public ToolFunction(String name, String description, Map<String, Parameter> parameters, List<String> required, Parameter returnParameter) {
        this.name = name;
        this.description = description;
        this.parameters = parameters;
        this.required = required;
        this.returnParameter = returnParameter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Parameter> parameters) {
        this.parameters = parameters;
    }

    public List<String> getRequired() {
        return required;
    }

    public void setRequired(List<String> required) {
        this.required = required;
    }

    public Parameter getReturnParameter() {
        return returnParameter;
    }

    public void setReturnParameter(Parameter returnParameter) {
        this.returnParameter = returnParameter;
    }
}


