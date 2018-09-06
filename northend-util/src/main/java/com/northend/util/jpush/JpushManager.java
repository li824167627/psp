package com.northend.util.jpush;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.PushPayload.Builder;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class JpushManager {
	static Logger logger = Logger.getLogger(JpushManager.class);

	// 蓝色岗亭
	private String appKey = "1559be5a5414c57fb18276e6";
	private String masterSecret = "d57d0c80e9c93fdd632a84d0";
	private boolean apnsProduction = false;

	private String apiUrl = "https://api.jpush.cn/v3/push"; // POST

	private static JpushManager instance = null;

	public JpushManager() {
	}

	public JpushManager(String appKey, String masterSecret, boolean apnsProduction) {
		this.appKey = appKey;
		this.masterSecret = masterSecret;
		this.apnsProduction = apnsProduction;
	}

	/**
	 * 获取单例
	 * 
	 * @param appKey
	 * @param masterSecret
	 * @param apnsProduction
	 *            是否生产环境
	 * @return
	 */
	public static JpushManager getInstance(String appKey, String masterSecret, boolean apnsProduction) {
		synchronized (JpushManager.class) {
			if (instance == null) {
				instance = new JpushManager(appKey, masterSecret, apnsProduction);
			}
			return instance;
		}
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		// testSendPushByAPI_CustomMessage();

		// testSendPushNotificationAndCustomMessage();

		Map<String, String> extras = new HashMap<>();
		extras.put("mid", "124232");
		extras.put("name", "wangwu");

		Set<String> alias = new HashSet<>();
		alias.add("f83832d395a344bd9aebd74f94c10df4_1");
		JpushManager jpushManager = JpushManager.getInstance("1559be5a5414c57fb18276e6", "d57d0c80e9c93fdd632a84d0",
				false);
		jpushManager.sendPushNotificationByAlias(alias, "通知标题");

		// sendPushCustomMessageByAlias(alias, "自定义消息内容", "自定义消息标题", extras);
	}

	/**
	 * 通过别名发送 自定义消息
	 * 
	 * @param alias
	 *            别名集合
	 * @param msgContent
	 *            消息内容本身
	 * @param msgTitle
	 *            消息标题
	 * @param extras
	 *            JSON 格式的可选参数
	 */
	public void sendPushCustomMessageByAlias(Set<String> alias, String msgContent, String msgTitle,
			Map<String, String> extras) {
		ClientConfig clientConfig = ClientConfig.getInstance();
		final JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, clientConfig);
		final PushPayload payload = buildPushObject_CustomMessageByAlias_android_and_ios(alias, msgContent, msgTitle,
				extras);
		try {
			PushResult result = jpushClient.sendPush(payload);
			logger.info("Got result - " + result);
			System.out.println(result);
		} catch (APIConnectionException e) {
			logger.error("Connection error. Should retry later. ", e);
			logger.error("Sendno: " + payload.getSendno());
		} catch (APIRequestException e) {
			logger.error("Error response from JPush server. Should review and fix it. ", e);
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Code: " + e.getErrorCode());
			logger.info("Error Message: " + e.getErrorMessage());
			logger.info("Msg ID: " + e.getMsgId());
			logger.error("Sendno: " + payload.getSendno());
		}
	}

	/**
	 * 构建 自定义消息发送对象
	 * 
	 * @return
	 */
	private PushPayload buildPushObject_CustomMessageByAlias_android_and_ios(Set<String> alias, String msgContent,
			String msgTitle, Map<String, String> extras) {
		Builder pushPayload = PushPayload.newBuilder();
		pushPayload.setPlatform(Platform.all())
				.setAudience(Audience.newBuilder().addAudienceTarget(AudienceTarget.alias(alias)).build());
		cn.jpush.api.push.model.Message.Builder message = Message.newBuilder();

		message.setMsgContent(msgContent);
		if (msgTitle != null) {
			message.setTitle(msgTitle);
		}
		message.setContentType("text");
		if (extras != null) {
			message.addExtras(extras);
		}

		pushPayload.setMessage(message.build());
		pushPayload
				.setOptions(Options.newBuilder().setTimeToLive(86400 * 10).setApnsProduction(apnsProduction).build());
		return pushPayload.build();
	}

	/**
	 * 通过别名 发送通知
	 * 
	 * @param alias
	 *            别名集合
	 * @param alert
	 *            通知标题
	 */
	public boolean sendPushNotificationByAlias(Set<String> alias, String alert) {
		ClientConfig clientConfig = ClientConfig.getInstance();
		final JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, clientConfig);
		final PushPayload payload = buildPushObject_NotificationByAlias_android_and_ios(alias, alert, alert, null, null,
				alert, null, null, null, null, null);
		try {
			PushResult result = jpushClient.sendPush(payload);
			result.getResponseCode();
			logger.info("Got result - " + result);
			System.out.println(result);
			return true;
		} catch (APIConnectionException e) {
			logger.error("Connection error. Should retry later. ", e);
			logger.error("Sendno: " + payload.getSendno());
		} catch (APIRequestException e) {
			logger.error("Error response from JPush server. Should review and fix it. ", e);
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Code: " + e.getErrorCode());
			logger.info("Error Message: " + e.getErrorMessage());
			logger.info("Msg ID: " + e.getMsgId());
			logger.error("Sendno: " + payload.getSendno());
		}
		return false;
	}

	/**
	 * 构建 通知发送对象
	 * 
	 * @param alias
	 *            用户的别名数组集合,给集合中的所有用户发送通知
	 * @param alert
	 *            基本-通知内容，android和ios会覆盖
	 * @param androidAlert
	 *            安卓标题
	 * @param androidTitle
	 *            alert 通知内容，如果安卓发送通知则必填
	 * @param androidExtras
	 *            extras 扩展字段
	 * @param iosAlert
	 *            alert 通知内容,如果要发送ios通知则必填
	 * @param iosExtras
	 *            extras 附加字段
	 * @param messageContent
	 *            msg_content 消息内容本身,如果要发送附加消息则必填
	 * @param messageTitle
	 *            title: 消息标题
	 * @param messageType--
	 *            content_type: 消息内容类型
	 * @param messageExtras
	 *            extras: JSON 格式的可选参数
	 * @return
	 */
	private PushPayload buildPushObject_NotificationByAlias_android_and_ios(Set<String> alias, String alert,
			String androidAlert, String androidTitle, Map<String, String> androidExtras, String iosAlert,
			Map<String, String> iosExtras, String messageContent, String messageTitle, String messageType,
			Map<String, String> messageExtras) {
		Builder pushPayload = PushPayload.newBuilder();
		pushPayload.setPlatform(Platform.all()).setAudience(Audience.alias(alias));

		// notification
		cn.jpush.api.push.model.notification.Notification.Builder notification = Notification.newBuilder();
		notification.setAlert(alert);

		// androidNotification
		cn.jpush.api.push.model.notification.AndroidNotification.Builder androidNotification = AndroidNotification
				.newBuilder();
		if (androidAlert != null) {
			androidNotification.setAlert(androidAlert);
			if (androidTitle != null) {
				androidNotification.setTitle(androidTitle).addExtras(androidExtras);
			}
			if (androidExtras != null) {
				androidNotification.setTitle(androidTitle).addExtras(androidExtras);
			}
			notification.addPlatformNotification(androidNotification.build());
		}

		// iosNotification
		cn.jpush.api.push.model.notification.IosNotification.Builder iosNotification = IosNotification.newBuilder();
		if (iosAlert != null) {
			iosNotification.setAlert(iosAlert);
			if (iosExtras != null) {
				iosNotification.addExtras(iosExtras);
			}
			notification.addPlatformNotification(iosNotification.build());
		}

		pushPayload.setNotification(notification.build());

		// message
		if (messageContent != null) {
			cn.jpush.api.push.model.Message.Builder message = Message.newBuilder();
			message.setMsgContent(messageContent);
			if (messageTitle != null) {
				message.setMsgContent(messageTitle);
			}
			if (messageType != null) {
				message.setContentType(messageType);
			}
			if (messageExtras != null) {
				message.addExtras(messageExtras);
			}
			pushPayload.setMessage(message.build());
		}

		return pushPayload.build();
	}

	private void testSendPushByAPI_CustomMessage() {
		JSONObject message = new JSONObject();
		message.put("platform", "all");
		message.put("audience", "all");
		JSONObject customMessage = new JSONObject();
		customMessage.put("msg_content", "test 自定义消息，磊哥测试");
		customMessage.put("title", "msg");
		customMessage.put("extras", " {\"key\": \"value\"}");
		message.put("message", customMessage);
		JSONObject options = new JSONObject();
		options.put("apns_production", false);
		message.put("options", options);
		String result = post(apiUrl, message.toJSONString());
		System.out.println(result);
	}

	// 自定义消息 start
	private void testSendPushCustomMessage() {
		ClientConfig clientConfig = ClientConfig.getInstance();
		final JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, clientConfig);
		final PushPayload payload = buildPushObject_CustomMessage_android_and_ios();
		try {
			PushResult result = jpushClient.sendPush(payload);
			logger.info("Got result - " + result);
			System.out.println(result);
		} catch (APIConnectionException e) {
			logger.error("Connection error. Should retry later. ", e);
			logger.error("Sendno: " + payload.getSendno());

		} catch (APIRequestException e) {
			logger.error("Error response from JPush server. Should review and fix it. ", e);
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Code: " + e.getErrorCode());
			logger.info("Error Message: " + e.getErrorMessage());
			logger.info("Msg ID: " + e.getMsgId());
			logger.error("Sendno: " + payload.getSendno());
		}
	}

	private PushPayload buildPushObject_CustomMessage_android_and_ios() {
		Map<String, String> extras = new HashMap<String, String>();
		extras.put("test", "https://community.jiguang.cn/push");
		return PushPayload.newBuilder().setPlatform(Platform.all())
				.setAudience(Audience.newBuilder()
						.addAudienceTarget(AudienceTarget.registrationId("1a1018970a9daf858b5")).build())
				.setMessage(Message.newBuilder().setMsgContent("自定义信息").addExtra("from", "JPush").build())
				.setOptions(Options.newBuilder().setTimeToLive(86400).setApnsProduction(false).build()).build();

	}
	// 自定义消息 end

	// 通知 加 自定义消息 start
	private void testSendPushNotificationAndCustomMessage() {
		ClientConfig clientConfig = ClientConfig.getInstance();

		final JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, clientConfig);
		// Here you can use NativeHttpClient or NettyHttpClient or ApacheHttpClient.
		// Call setHttpClient to set httpClient,
		// If you don't invoke this method, default httpClient will use
		// NativeHttpClient.
		// ApacheHttpClient httpClient = new ApacheHttpClient(authCode, null,
		// clientConfig);
		// jpushClient.getPushClient().setHttpClient(httpClient);
		final PushPayload payload = buildPushObject_NotificationAndCustomMessage_android_and_ios();
		// // For push, all you need do is to build PushPayload object.
		// PushPayload payload = buildPushObject_all_alias_alert();
		try {
			PushResult result = jpushClient.sendPush(payload);
			logger.info("Got result - " + result);
			System.out.println(result);
			// 如果使用 NettyHttpClient，需要手动调用 close 方法退出进程
			// If uses NettyHttpClient, call close when finished sending request, otherwise
			// process will not exit.
			// jpushClient.close();
		} catch (APIConnectionException e) {
			logger.error("Connection error. Should retry later. ", e);
			logger.error("Sendno: " + payload.getSendno());

		} catch (APIRequestException e) {
			logger.error("Error response from JPush server. Should review and fix it. ", e);
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Code: " + e.getErrorCode());
			logger.info("Error Message: " + e.getErrorMessage());
			logger.info("Msg ID: " + e.getMsgId());
			logger.error("Sendno: " + payload.getSendno());
		}
	}

	private PushPayload buildPushObject_NotificationAndCustomMessage_android_and_ios() {
		Map<String, String> extras = new HashMap<String, String>();
		extras.put("test", "https://community.jiguang.cn/push");
		return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.all())
				.setNotification(Notification.newBuilder().setAlert("alert content")
						.addPlatformNotification(
								AndroidNotification.newBuilder().setTitle("Android Title").addExtras(extras).build())
						.addPlatformNotification(IosNotification.newBuilder().setContentAvailable(true).incrBadge(1)
								.addExtra("extra_key", "extra_value").build())
						.build())
				.setMessage(Message.newBuilder().setMsgContent("自定义信息").addExtra("from", "JPush").build()).build();
	}
	// 通知 加 自定义消息 end

	private String get(String uri, Map<String, Object> params) {
		// 参数处理
		if (params != null && !params.isEmpty()) {
			StringBuffer sb = new StringBuffer();
			for (String key : params.keySet()) {
				sb.append("&" + key + "=" + params.get(key));
			}
			uri = uri + "?" + sb.toString().substring(1);
		}
		logger.info("http get uri is:" + uri);
		// 获取当前客户端对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// get
		HttpGet request = new HttpGet(uri);

		try {
			byte[] encodeBytes = Base64.encodeBase64((appKey + ":" + masterSecret).getBytes());
			request.addHeader("Authorization", "Basic " + new String(encodeBytes, "UTF-8"));

			CloseableHttpResponse response = httpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				String value = entity != null ? EntityUtils.toString(entity) : null;
				/*
				 * if (value != null) value = new String(value.getBytes("ISO-8859-1"), "utf-8");
				 */
				return value;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private String post(String uri, String data) {
		// 获取当前客户端对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// post
		HttpPost request = new HttpPost(uri);
		// 通过请求对象获取响应对象
		try {

			byte[] encodeBytes = Base64.encodeBase64((appKey + ":" + masterSecret).getBytes());
			request.addHeader("Authorization", "Basic " + new String(encodeBytes, "UTF-8"));

			request.setEntity(new StringEntity(data, ContentType.create("application/json", Consts.UTF_8)));
			logger.info("post string data is ：" + data);

			HttpResponse response = httpClient.execute(request);
			Header[] headers = response.getAllHeaders();
			for (Header header : headers) {
				logger.info("response [header] " + header.getName() + " " + header.getValue());
			}
			logger.info("response [status]:" + response.getStatusLine());
			logger.info("response [entity]:" + response.getEntity().toString());
			if (response.getStatusLine().getStatusCode() == 200) {
				return EntityUtils.toString(response.getEntity());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
