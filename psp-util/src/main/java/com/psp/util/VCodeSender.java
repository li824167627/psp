package com.psp.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

/**
 * 创蓝 235 短信发送工具类
 * 
 * @param url
 *            应用地址，类似于http://ip:port/msg/
 * @param un
 *            账号
 * @param pw
 *            密码
 * @param phone
 *            手机号码，多个号码使用","分割
 * @param msg
 *            短信内容
 * @param rd
 *            是否需要状态报告，需要1，不需要0
 * @return 返回值定义参见HTTP协议文档
 * @throws Exception
 */
public class VCodeSender {
	private String userName;
	private String passWord;

	private static VCodeSender instance;

	public VCodeSender(String un, String pw) {
		if (un != null) {
			this.userName = un;
		}
		if (pw != null) {
			this.passWord = pw;
		}
	}

	public static VCodeSender getInstance(String un, String pw) {
		synchronized (VCodeSender.class) {
			if (instance == null)
				instance = new VCodeSender(un, pw);
			return instance;
		}
	}

	public boolean send(String phone, String msg, String ex) {
		String url = "http://sms.253.com/msg/send";

		String rd = "1";
		CloseableHttpClient client = HttpClientBuilder.create().build();
		// 创建httppost
		HttpPost httpPost = new HttpPost(url);
		// 创建参数队列
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		try {
			params.add(new BasicNameValuePair("un", userName));
			params.add(new BasicNameValuePair("pw", passWord));
			params.add(new BasicNameValuePair("phone", phone));
			params.add(new BasicNameValuePair("rd", rd));
			params.add(new BasicNameValuePair("msg", msg));
			params.add(new BasicNameValuePair("ex", ex));
			CloseableHttpResponse response = null;
			try {
				HttpEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
				httpPost.setEntity(entity);
				response = client.execute(httpPost);
				StatusLine result = response.getStatusLine();
				if (result.getStatusCode() == HttpStatus.SC_OK) {
					InputStream in = response.getEntity().getContent();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = in.read(buffer)) != -1) {
						baos.write(buffer, 0, len);
					}
					String json = URLDecoder.decode(baos.toString(), "UTF-8");
					System.out.println(json);
					return true;
				} else {
					Logger.getLogger("vcode sender:" + "HTTP ERROR Status: " + result.getStatusCode() + ":"
							+ result.getReasonPhrase());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} finally {
			httpPost.releaseConnection();
		}
		return false;
	}
}