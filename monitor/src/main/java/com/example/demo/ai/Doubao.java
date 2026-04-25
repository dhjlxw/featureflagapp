package com.example.demo.ai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Doubao {

	// 替换为你的实际 API 密钥
	private static final String API_KEY = "19812102-fe24-4595-a212-358b8b67d24d";
	// 替换为你的实际模型接入点
	// doubao-1.5-pro-32k-250115
	// doubao-1.5-pro-256k-250115
	private static final String MODEL_ENDPOINT = "doubao-1.5-lite-32k-250115";
	private static final String BASE_URL = "https://ark.cn-beijing.volces.com/api/v3/chat/completions";

	public static String invoke(String question) {
		Map<String, Object> requestBody = getRequestMap(question);
		String requestJson = JsonUtil.toPrettyString(requestBody);
		Map<String, String> headers = new LinkedHashMap<>();
		headers.put("Authorization", "Bearer " + API_KEY);
		headers.put("Content-Type", "application/json");
		System.out.println(requestJson);
		String response = NetUtil.callUrl(headers, BASE_URL, requestJson);
		System.out.println(response);
		Map<String, Object> map = JsonUtil.toObject(response, Map.class);
		List<Map<String, Object>> choices = (List<Map<String, Object>>) map.get("choices");
		Map<String, Object> answer = (Map<String, Object>) choices.iterator().next().get("message");
		return (String) answer.get("content");
	}

	private static Map<String, Object> getRequestMap(String question) {
		// 构建请求体
		Map<String, Object> requestBody = new LinkedHashMap<>();
		requestBody.put("model", MODEL_ENDPOINT);
		// requestBody.put("temperature", 0.5);
		// requestBody.put("max_tokens", 400);

		List<Map<String, String>> messages = new ArrayList<>();
		Map<String, String> systemMessage = new HashMap<>();
		systemMessage.put("role", "system");
		systemMessage.put("content", "你是豆包，是由字节跳动开发的AI人工智能助手");
		messages.add(systemMessage);

		Map<String, String> userMessage = new HashMap<>();
		userMessage.put("role", "user");
		userMessage.put("content", question);
		messages.add(userMessage);

		requestBody.put("messages", messages);
		return requestBody;
	}

	public static void main(String[] args) {
		String question = "请翻译为英文:今天是个好日子";
		String answer = invoke(question);
		if (answer != null) {
			System.out.println(answer);
		}
	}
}
