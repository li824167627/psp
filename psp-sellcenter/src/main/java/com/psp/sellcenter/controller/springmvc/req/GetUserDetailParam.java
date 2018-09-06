package com.psp.sellcenter.controller.springmvc.req;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 获取客户基本信息
 **/
public class GetUserDetailParam {
	@NotEmpty(message = "客户id不能为空")
	private String uid; // 客户ID

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUid() {
		return uid;
	}

}
