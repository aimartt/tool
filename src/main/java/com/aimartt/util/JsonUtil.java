/**
 * 
 */
package com.aimartt.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json 工具类。
 * @author aimartt
 * @date 2015-01-05
 * @category Json 工具类
 */
public class JsonUtil {
	
	private static final JsonFactory FACTORY = new ObjectMapper().getFactory();
	
	/**
	 * Json 格式字符串转换为 Map。
	 * @param json Json 格式字符串
	 * @return
	 * @category Json 格式字符串转换为 Map
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> parse(String json) {
		Map<String, Object> map = Collections.emptyMap();
		try {
			JsonParser parser = FACTORY.createParser(json);
			map = parser.readValueAs(Map.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * Java 对象转换成 Json 格式字符串。
	 * @param pojo java 对象
	 * @return 
	 * @category Java 对象转换成 Json 格式字符串
	 */
	public static String toJson(Object pojo) {
		StringWriter writer = new StringWriter();
		try {
			JsonGenerator generator = FACTORY.createGenerator(writer);
			generator.writeObject(pojo);
			writer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return writer.toString();
	}
	
	private JsonUtil() {
	}

}