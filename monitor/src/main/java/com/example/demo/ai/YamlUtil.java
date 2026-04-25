package com.example.demo.ai;

import java.util.HashMap;

import java.util.Map;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

public class YamlUtil {

	private static final ObjectMapper objectMapper;
	static {
		YAMLFactory factory = new YAMLFactory();
		factory.configure(YAMLGenerator.Feature.MINIMIZE_QUOTES, true); // 最小化引号
		factory.configure(YAMLGenerator.Feature.ALWAYS_QUOTE_NUMBERS_AS_STRINGS, false);
		objectMapper = new ObjectMapper(factory);

		// objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS,
		// false);
		// objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
		// false);
	}

	public static <T> T toObject(String str, Class<T> cls) {
		T ret = null;
		if (!AIUtil.isEmpty(str)) {
			ret = getObjectFromJson(str, cls);
		}
		return ret;
	}

	public static <T> T getObjectFromJson(String str, Class<T> cls) {
		try {
			T t = objectMapper.readValue(str, cls);
			return t;
		} catch (Exception e) {
			throw new RuntimeException("parse json error, json=" + str + ", class=" + cls.getName(), e);
		}
	}

	public static String toString(Object o) {
		String ret = null;
		if (o != null) {
			ret = getJsonFromObject(o);
		}
		return ret;
	}

	public static String getJsonFromObject(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException("get json error", e);
		}
	}

	public static void main(String[] args) {
		Map m = new HashMap();
		m.put("ABC", "HELLO");
		m.put("DEF", "WORLD");
		HashMap map = new HashMap<>();
		map.put("NUMBER", 1);
		map.put("BOOL", true);
		map.put("SUB", "HELLO:ABC\nText:DEF\nWorld");
		m.put("NEXT", map);
		System.out.println(YamlUtil.toString(m));
		String v = YamlUtil.toString(m);
		System.out.println(YamlUtil.toObject(v, Map.class));
	}

}
