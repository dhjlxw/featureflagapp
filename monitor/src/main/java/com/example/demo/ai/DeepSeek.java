package com.example.demo.ai;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DeepSeek {

	private static final String API_URL = "https://api.deepseek.com/chat/completions";
	private static final String API_KEY = "sk-e5cd296a6d0b4815b4ae6186d721e8d2"; // 请替换为实际的 API Key

	public static String invoke(String question) throws Exception {
		return DeepSeek.callDeepSeekAPI("", question, false);
	}

	public static String callDeepSeekAPI(String prompt, String txt, boolean reasoning) throws IOException {
		String userMessage = prompt + ":" + txt;
		if (StringUtil.isEmpty(prompt)) {
			userMessage = txt;
		}
		if (StringUtil.isEmpty(userMessage)) {
			System.out.println("DeepSeek message is null, so ignore");
			return "";
		}
		System.out.println("Deepseek sending:" + userMessage.length() + ",reasoning:" + reasoning + ","
				+ (userMessage.length() < 200 ? userMessage : userMessage.substring(0, 200)));
		URL url = new URL(API_URL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// 设置请求方法为 POST
		connection.setRequestMethod("POST");
		// 设置请求头
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Authorization", "Bearer " + API_KEY);

		// 允许输出请求体
		connection.setDoOutput(true);

		// 构建请求体

		Map<String, Object> requestBodyMap = new LinkedHashMap<>();
		if (reasoning) {
			requestBodyMap.put("model", "deepseek-reasoner");
		} else {
			requestBodyMap.put("model", "deepseek-chat");

		}
		List<Map<String, String>> messages = new ArrayList<>();
		Map<String, String> systemMessage = new LinkedHashMap<>();
		systemMessage.put("role", "system");
		systemMessage.put("content", "You are a helpful assistant.");
		messages.add(systemMessage);

		Map<String, String> userMessageMap = new LinkedHashMap<>();
		userMessageMap.put("role", "user");
		userMessageMap.put("content", userMessage);
		messages.add(userMessageMap);

		requestBodyMap.put("messages", messages);
		requestBodyMap.put("stream", false);
		String requestBody = JsonUtil.toString(requestBodyMap);
		// userMessage = "hello";
		// String requestBody = "{\"model\": \"deepseek-chat\", \"messages\":
		// [{\"role\": \"system\", \"content\": \"You are a helpful assistant.\"},
		// {\"role\": \"user\", \"content\": \""
		// + userMessage + "\"}], \"stream\": false}";
		System.out.println("Deepseek request:" + requestBody);
		// 写入请求体
		try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
			wr.write(requestBody.getBytes("UTF-8"));
			wr.flush();
		}

		// 获取响应状态码
		int responseCode = connection.getResponseCode();
		System.out.println("Deepseek Response:" + responseCode);
		// 读取响应内容
		StringBuilder response = new StringBuilder();

		try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
		}

		String resp = response.toString();
		System.out.println("Deepseek response(" + responseCode + ")(" + resp.length() + "):" + resp);
		Map map = JsonUtil.toObject(resp, Map.class);
		List<Map> list = new ArrayList((Collection) map.get("choices"));
		System.out.println("Deepseek choice size:" + list.size());
		Map first = first(list);
		String ret = "";
		if (first != null) {
			Map item = (Map) first.get("message");
			if (item != null) {
				ret = (String) item.get("content");
			}
		}
		System.out.println("Deepseek return:" + ret);
		return ret;
	}

	public static <T extends Object> T first(Collection<T> col) {
		if (col == null || col.isEmpty()) {
			return null;
		}
		return col.iterator().next();
	}

	public static void main(String[] args) {
		try {
			String userMessage = "";
			String response = callDeepSeekAPI("怎样通过大模型写一个pdf的总结", userMessage, false);
			System.out.println("API 响应: " + response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}