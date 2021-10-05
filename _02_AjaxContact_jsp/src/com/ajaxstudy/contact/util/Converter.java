package com.ajaxstudy.contact.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.google.gson.Gson;

public class Converter {
	private static Gson gson;
	//static block gson 객체 초기화
	static {
		gson = new Gson();
	}
	
	// JAVA 객체 -> Json 문자열로 변환
	public static String convertToJson(Object obj) {
		return gson.toJson(obj);
	}
	
	// Json 문자열 -> (해당 클래스 타입의)JAVA 객체로 변환
	public static <T> T convertFromJson(String json, Class<T> type) {
		return gson.fromJson(json, type);
	}
	
	// 브라우저와 연결된 스트림에서 전송된 Json 문자열 -> (해당 클래스 타입의)JAVA 객체로 변환
	public static <T> T convertFromJsonStream(InputStream is, Class<T> type) {
		try {
			Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			return gson.fromJson(reader, type);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
