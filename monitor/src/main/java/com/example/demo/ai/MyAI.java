package com.example.demo.ai;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class MyAI {

	public static String invoke(String question) throws Exception {
		return MyAI.callMyAIAPI("", question, false);
	}

	public static String callMyAIAPI(String prompt, String txt, boolean reasoning) throws IOException {
		String userMessage = prompt + ":" + txt;
		if (AIUtil.isEmpty(prompt)) {
			userMessage = txt;
		}
		if (AIUtil.empty(userMessage)) {
			System.out.println("MyAI message is null, so ignore");
			return "";
		}
		System.out.println("MyAI sending:" + userMessage.length() + ",reasoning:" + reasoning + ","
				+ (userMessage.length() < 200 ? userMessage : userMessage.substring(0, 200)));

		// 构建请求体

		Map<String, Object> requestBodyMap = new LinkedHashMap<>();

		requestBodyMap.put("model", "/run/dhj/Qwen2___5-14B-Instruct/");

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
		System.out.print(requestBody);
		
		String resp = getResponse(requestBody);

		Map map = JsonUtil.toObject(resp, Map.class);
		List<Map> list = new ArrayList((Collection) map.get("choices"));
		System.out.println("MyAI choice size:" + list.size());
		Map first = AIUtil.first(list);
		String ret = "";
		if (first != null) {
			Map item = (Map) first.get("message");
			if (item != null) {
				ret = (String) item.get("content");
			}
		}
		System.out.println("MyAI return:" + ret);
		return ret;
	}

	public static String getResponse(String requestBody) {
		String resp="{}";
		try {
			AIUtil.writeToFile(requestBody, new File("/Users/dinghaijiang/ding/ai/Test/request.json"));
			AIUtil.runCommand(new String[] {"/Users/dinghaijiang/ding/ai/Test/openai.sh"}, null);
			resp = AIUtil.read("/Users/dinghaijiang/ding/ai/Test/response.json");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return resp.trim();
	}

	public static void main(String[] args) {
		try {
			String userMessage = "";
			String response = callMyAIAPI("请写一个工作经历", userMessage, false);
			System.out.println("API 响应: " + response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}