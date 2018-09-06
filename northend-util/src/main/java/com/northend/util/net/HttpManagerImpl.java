package com.northend.util.net;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpManagerImpl implements HttpManager {
	Logger logger = Logger.getLogger(HttpManagerImpl.class);
	
	private static HttpManagerImpl instance;

	private HttpManagerImpl() {
	}

	public static HttpManagerImpl getInstance() {
		synchronized (HttpManagerImpl.class) {
			if (instance == null) {
				instance = new HttpManagerImpl();
			}
			return instance;
		}
	}

	@Override
	public String post(String uri, Map<String, Object> params) {
		// 获取当前客户端对象
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// post
		HttpPost request = new HttpPost(uri);
		// 参数处理
		List<NameValuePair> temp = new ArrayList<NameValuePair>();

		for (String key : params.keySet()) {
			temp.add(new BasicNameValuePair(key, String.valueOf(params.get(key))));
		}
		request.setEntity(new UrlEncodedFormEntity(temp, StandardCharsets.UTF_8));
		// 通过请求对象获取响应对象
		try {
			HttpResponse response = httpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				return EntityUtils.toString(response.getEntity());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String post(String uri, String data) {
		// 获取当前客户端对象
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// post
		HttpPost request = new HttpPost(uri);
		request.setEntity(new StringEntity(data, ContentType.create("text/html", Consts.UTF_8)));
		logger.info("post string data is ：" + data);
		// 通过请求对象获取响应对象
		try {
			HttpResponse response = httpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				return EntityUtils.toString(response.getEntity());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String get(String uri, Map<String, Object> params) {
		// 参数处理
		if (params != null && !params.isEmpty()) {
			StringBuffer sb = new StringBuffer();
			for (String key : params.keySet()) {
				sb.append("&" + key + "=" + params.get(key));
			}
			uri = uri + "?" + sb.toString().substring(1);

		}
		Logger.getLogger(HttpManagerImpl.class).info("http get uri is:" + uri);
		// 获取当前客户端对象
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// get
		HttpGet request = new HttpGet(uri);
		// 通过请求对象获取响应对象
		try {
			HttpResponse response = httpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				String value = entity != null ? EntityUtils.toString(entity) : null;
				if (value != null)
					value = new String(value.getBytes("ISO-8859-1"), "utf-8");
				return value;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String post(String uri, Map<String, Object> params, String data) {
		// 参数处理
		if (params != null && !params.isEmpty()) {
			StringBuffer sb = new StringBuffer();
			for (String key : params.keySet()) {
				sb.append("&" + key + "=" + params.get(key));
			}
			uri = uri + "?" + sb.toString().substring(1);

		}
		return post(uri, data);
	}

	/**
	 * 下载文件
	 *
	 * @param url
	 *            URL
	 * @return 文件的二进制流，客户端使用outputStream输出为文件
	 */
	public byte[] getFile(String uri, Map<String, Object> params) {
		// 参数处理
		if (params != null && !params.isEmpty()) {
			StringBuffer sb = new StringBuffer();
			for (String key : params.keySet()) {
				sb.append("&" + key + "=" + params.get(key));
			}
			uri = uri + "?" + sb.toString().substring(1);
		}
		Logger.getLogger(HttpManagerImpl.class).info("http get uri is:" + uri);
		// 获取当前客户端对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// get
		HttpGet request = new HttpGet(uri);
		// 通过请求对象获取响应对象
		try {
			HttpResponse response = httpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				return EntityUtils.toByteArray(entity);
			} else {
				Logger.getLogger(HttpManagerImpl.class)
						.info("请求服务器失败，错误代码为：" + response.getStatusLine().getStatusCode());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
