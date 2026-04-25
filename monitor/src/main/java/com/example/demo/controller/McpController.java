package com.example.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/mcp")
public class McpController {

    @GetMapping("/discover")
    public Map<String, Object> discover() {
        return Map.of(
            "name", "McpController",
            "version", "1.0.0",
            "tools", List.of(
                Map.of(
                    "name", "echo",
                    "description", "Echoes back the input message",
                    "inputSchema", EchoTool.InputSchema.class
                )
            )
        );
    }
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    @PostMapping(value = "/echo", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter echo(@RequestBody Map<String, Object> request) {
        SseEmitter emitter = new SseEmitter();
        String clientId = request.get("clientId").toString();
        emitters.put(clientId, emitter);

        emitter.onCompletion(() -> emitters.remove(clientId));
        emitter.onTimeout(() -> emitters.remove(clientId));
        emitter.onError((e) -> emitters.remove(clientId));

        try {
            String message = request.get("message").toString();
            emitter.send(SseEmitter.event()
                    .name("echo")
                    .data(message));
        } catch (Exception e) {
            emitter.completeWithError(e);
        }

        return emitter;
    }

    public static class EchoTool {
        public String name = "echo";
        public String description = "Echoes back the input message";
        
        public static class InputSchema {
            public String type = "object";
            public Map<String, Property> properties = Map.of(
                "message", new Property("string", "The message to echo back")
            );
            public String[] required = {"message"};
        }

        static class Property {
            String type;
            String description;
            
            Property(String type, String description) {
                this.type = type;
                this.description = description;
            }
        }
    }
}
