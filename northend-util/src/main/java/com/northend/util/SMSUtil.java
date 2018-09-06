package com.northend.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.http.Header;
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

public class SMSUtil {

	private static String url = "http://utf8.sms.webchinese.cn";
	private static String uid = "chekucafe@hotmail.com";
	private static String charset = "utf8";
	private static String key = "ecfcb5c3cd97ed45180f";

	public static void main(String[] args) {
		SMSUtil.send("18010483452", "您好，您的验证码是123456");
	}

	public static boolean send(String phone, String msg) {
		boolean res = false;

		CloseableHttpClient client = HttpClientBuilder.create().build();
		// 创建httppost
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
		// 创建参数队列
		List<NameValuePair> params = new ArrayList<NameValuePair>();

		try {
			params.add(new BasicNameValuePair("Uid", uid));
			params.add(new BasicNameValuePair("Key", key));
			params.add(new BasicNameValuePair("smsMob", phone));
			params.add(new BasicNameValuePair("smsText", msg));
			CloseableHttpResponse response = null;

			try {
				HttpEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
				httpPost.setEntity(entity);
				
				response = client.execute(httpPost);
				Header[] headers = response.getAllHeaders();
				for (Header h : headers) {
					System.out.println(h.toString());
				}
				StatusLine result = response.getStatusLine();
				System.out.println(result);
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

		return res;

	}
}
