package com.northend.util.jpush;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.northend.util.BeanUtil;

public class Test {

	public static void main(String[] args) {
		JpushManager jpush = JpushManager.getInstance("1559be5a5414c57fb18276e6", "d57d0c80e9c93fdd632a84d0", false);

		// 通知
		Set<String> alias = new HashSet<>();
		alias.add(""); //
		String alert = "通知";
		jpush.sendPushNotificationByAlias(alias, alert);

		// 消息
		alias = new HashSet<>();
		alias.add("uid"); // 用户别名 uid

		CustomeMessageBase<String> pushMessage = new CustomeMessageBase<String>();
		pushMessage.setMid("123123"); // 消息id
		pushMessage.setTitle(alert); // 消息标题
		pushMessage.setSubTitle(alert); // 子标题
		pushMessage.setType(1); // 消息自定义类型
		pushMessage.setPhone("1888888888");
		pushMessage.setCreateTime(System.currentTimeMillis() / 1000);

		RNoticeBean messageData = new RNoticeBean();
		messageData.setFaid("");
		messageData.setAddType(2);
		messageData.setIcon("");
		messageData.setLetter("");
		messageData.setName("");
		messageData.setState(0);
		pushMessage.setData(JSON.toJSONString(messageData));

		jpush.sendPushCustomMessageByAlias(alias, alert, alert, BeanUtil.beanToMapString(pushMessage));

	}

}
