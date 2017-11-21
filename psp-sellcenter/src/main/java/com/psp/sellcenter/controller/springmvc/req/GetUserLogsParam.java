package com.psp.sellcenter.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 获取客户操作日志
 **/
public class GetUserLogsParam {
	@NotEmpty(message = "客户id不能为空")
	private String uid; // 客户ID
	private String key; // 关键字

	public void setUid(String uid) {
 		this.uid = uid;
	}

	public String getUid() {
 		return uid;
	}

	public void setKey(String key) {
 		this.key = key;
	}

	public String getKey() {
 		return key;
	}

}
