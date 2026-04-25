package com.example.demo.ai.beans.response;

public class Choice {
    private int index;
    private Message message;
    private Object logprobs;
    private String finish_reason;
    private int stop_reason;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Object getLogprobs() {
        return logprobs;
    }

    public void setLogprobs(Object logprobs) {
        this.logprobs = logprobs;
    }

    public String getFinish_reason() {
        return finish_reason;
    }

    public void setFinish_reason(String finish_reason) {
        this.finish_reason = finish_reason;
    }

    public int getStop_reason() {
        return stop_reason;
    }

    public void setStop_reason(int stop_reason) {
        this.stop_reason = stop_reason;
    }
}
