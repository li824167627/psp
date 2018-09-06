package com.northend.api.go.type;

import java.util.HashMap;
import java.util.Map;

public class Types {

	private static Map<String, String[]> types;
	private static final String list = "list";
	private static final String map = "map";
	
	private static final String jsonArray = "jsonArray";

	static {
		types = new HashMap<>();
		types.put("string", new String[] { "java.lang.String", "String" });
		types.put("integer", new String[] { "java.lang.Integer", "Integer" });
		types.put("int", new String[] { "java.lang.Integer", "Integer" });
		types.put("float", new String[] { "java.lang.Float", "Float" });
		types.put("long", new String[] { "java.lang.Long", "Long" });
		types.put("byte", new String[] { "java.lang.Byte", "Byte" });
		types.put("double", new String[] { "java.lang.Double", "Double" });
		types.put("boolean", new String[] { "java.lang.Boolean", "Boolean" });
		
		types.put("jsonArray", new String[] { "com.alibaba.fastjson.JSONArray","JSONArray" });
	}

	public static String[] getType(String key) {
		return types.get(key);
	}

	public static boolean isExist(String key) {
		return types.containsKey(key);
	}

	public static boolean isObject(String key) {
		return !types.containsKey(key);
	}

	public static boolean isList(String key) {
		return list.equalsIgnoreCase(key);
	}

	public static boolean isMap(String key) {
		return map.equalsIgnoreCase(key);
	}
	
	public static boolean isJsonArray(String key){
		return jsonArray.equalsIgnoreCase(key);
	}

	public static String getValue(String key, String value) {
		if ("string".equals(key)) {
			return "\"" + value + "\"";
		} else if ("float".equals(key)) {
			return value + "f";
		} else if ("double".equals(key)) {
			return value + "d";
		}
		return value;
	}

}
