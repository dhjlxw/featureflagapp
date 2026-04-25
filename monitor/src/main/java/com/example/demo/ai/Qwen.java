package com.example.demo.ai;

import java.util.LinkedHashMap;
import java.util.Map;


public class Qwen {
	private static final String BASE_URL = "http://localhost:11434/api/generate";
	private static final Object MODEL_ENDPOINT = "qwen2.5-coder:14b";

	public static String invoke(String question) {
		Map<String, Object> requestBody = getRequestMap(question);
		String requestJson = JsonUtil.toPrettyString(requestBody);
		Map<String, String> headers = new LinkedHashMap();
		headers.put("Content-Type", "application/json");
		System.out.println(requestJson);
		String response = NetUtil.callUrl(headers, BASE_URL, requestJson);
		System.out.println(response);
		Map<String, Object> map = JsonUtil.toObject(response, Map.class);
		return (String) map.get("response");
	}

	private static Map<String, Object> getRequestMap(String question) {
		Map<String, Object> requestBody = new LinkedHashMap<>();
		requestBody.put("model", MODEL_ENDPOINT);
		requestBody.put("prompt", question);
		requestBody.put("stream", false);
		return requestBody;
	}

	public static void main(String[] args) {
		System.out.println(invoke("请把introduce这个单词的词根列出来，并给出意思，要求返回格式为词根1:意思,词根2:意思，不要返回额外的东西，返回为一个JSON格式"));
	}

}
