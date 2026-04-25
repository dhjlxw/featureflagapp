package com.example.demo.ai;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class NetUtil {

    public static String callUrl(Map<String, String> headers, String url, String data) {
		try {
			System.out.println(String.format("Call Url %s", url));
			HttpURLConnection uc = (HttpURLConnection) new URL(url).openConnection();
			if (headers != null) {
				for (Map.Entry<String, String> h : headers.entrySet()) {
					uc.setRequestProperty(h.getKey(), h.getValue());
				}
			}
			uc.setDoInput(true);
			if (data != null) {
				System.out.println(data);
				uc.setDoOutput(true);
				OutputStream out = uc.getOutputStream();
				out.write(data.replaceAll("\t", "\\t").getBytes("utf-8"));
				out.flush();
			}
			int code = uc.getResponseCode();
			InputStream in = isSuccess(code) ? uc.getInputStream() : uc.getErrorStream();
			return read(in);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String callUrl(String url, String data) {
		try {
			System.out.println(String.format("Call Url %s", url));
			HttpURLConnection uc = (HttpURLConnection) new URL(url).openConnection();
			uc.setDoInput(true);
			if (data != null) {
				System.out.println(data);
				uc.setDoOutput(true);
				uc.addRequestProperty("SOAPAction", "null");
				OutputStream out = uc.getOutputStream();
				out.write(data.getBytes());
				out.flush();
			}
			int code = uc.getResponseCode();
			InputStream in = isSuccess(code) ? uc.getInputStream() : uc.getErrorStream();
			return read(in);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Map callMap(String url, Map data) {
		String resp = callUrl(url, data == null ? null : JsonUtil.toString(data), 1, true);
		return StringUtil.isEmpty(resp) ? new HashMap() : JsonUtil.toObject(resp, Map.class);
	}

	public static String callUrl(String url, String data, int nTimes, boolean json) {
		url = "http://".concat(url.substring(7).replace("//", "/"));
		System.out.println("CALL:" + url);
		System.out.println("DATA:" + data);
		Throwable t = null;
		for (int i = 0; i < nTimes; i++) {
			try {
				HttpURLConnection uc = (HttpURLConnection) new URL(url).openConnection();
				uc.setConnectTimeout(40000);
				uc.setReadTimeout(140000);
				if (json) {
					uc.setRequestProperty("content-type", "application/json");
				}
				uc.setDoInput(true);
				if (data != null) {
					uc.setRequestMethod("POST");
					uc.setDoOutput(true);
					// uc.addRequestProperty("SOAPAction", "null");
					OutputStream out = uc.getOutputStream();
					out.write(data.getBytes("UTF-8"));
					out.flush();
				}
				int code = uc.getResponseCode();
				InputStream in = isSuccess(code) ? uc.getInputStream() : uc.getErrorStream();
				String resp = read(in);
				System.out.println("RESP:" + resp);
				return resp;
			} catch (Exception e) {
				e.printStackTrace();
				t = e;
			}
		}
		if (t != null) {
			throw new RuntimeException(String.format("%s:%s", url,t));
		}
		return null;
	}
	public static Object[] callStream(String url, String data, int nTimes, boolean json) {
		url = "http://".concat(url.substring(7).replace("//", "/"));
		System.out.println("CALL:" + url);
		System.out.println("DATA:" + data);
		Throwable t = null;
		for (int i = 0; i < nTimes; i++) {
			try {
				HttpURLConnection uc = (HttpURLConnection) new URL(url).openConnection();
				uc.setConnectTimeout(400000);
				uc.setReadTimeout(1400000);
				if (json) {
					uc.setRequestProperty("content-type", "application/json");
				}
				uc.setDoInput(true);
				if (data != null) {
					uc.setRequestMethod("POST");
					uc.setDoOutput(true);
					// uc.addRequestProperty("SOAPAction", "null");
					OutputStream out = uc.getOutputStream();
					out.write(data.getBytes("UTF-8"));
					out.flush();
				}
				int code = uc.getResponseCode();
				System.out.println("RESPONSE_CODE:"+code+":"+uc.getHeaderFields());
				InputStream in = isSuccess(code) ? uc.getInputStream() : uc.getErrorStream();
				return new Object[]{uc.getHeaderFields(),in};
			} catch (Exception e) {
				e.printStackTrace();
				t = e;
			}
		}
		if (t != null) {
			throw new RuntimeException(String.format("%s:%s", url, t));
		}
		return null;
	}
	public static String read(InputStream in, int len) {
		try {
			byte[] b = new byte[1024];
			int c = -1;
			int sum = 0;
			ByteArrayOutputStream o = new ByteArrayOutputStream();
			while ((c = in.read(b)) != -1) {
				if (len == -1 || sum < len) {
					o.write(b, 0, c);
					sum += c;
				}
			}
			return o.toString("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static String read(InputStream in) {
		try {
			byte[] b = new byte[1024];
			int c = -1;
			ByteArrayOutputStream o = new ByteArrayOutputStream();
			while ((c = in.read(b)) != -1) {
				o.write(b, 0, c);
			}
//			System.out.println("GBK::");
//			System.out.println(StringUtil.encoding(o.toString("GBK")));
//			System.out.println("UTF8::");
//			System.out.println(StringUtil.encoding(o.toString("UTF-8")));
//			System.out.println("8859_1::");
//			System.out.println(StringUtil.encoding(o.toString("8859_1")));
			return o.toString("UTF-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void write(OutputStream out, String str) throws Exception {
		out.write(str.getBytes());
		out.close();
	}

	private static boolean isSuccess(int code) {
		return code == 200;
	}




}
