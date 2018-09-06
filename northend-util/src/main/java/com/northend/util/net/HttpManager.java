package com.northend.util.net;

import java.util.Map;

public interface HttpManager {

	/**
	 * post 请求
	 * 
	 * @param uri
	 * @param params
	 */
	String post(String uri, Map<String, Object> params);

	/**
	 * post 请求string
	 * 
	 * @param uri
	 * @param data
	 * @return
	 */
	String post(String uri, String data);

	/**
	 * post 请求
	 * 
	 * @param uri
	 * @param params
	 * @param data
	 * @return
	 */
	String post(String uri, Map<String, Object> params, String data);

	/**
	 * get 请求
	 * 
	 * @param uri
	 * @param params
	 * @return
	 */
	String get(String uri, Map<String, Object> params);
}
