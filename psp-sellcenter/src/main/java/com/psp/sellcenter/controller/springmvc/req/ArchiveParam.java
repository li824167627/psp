package com.psp.sellcenter.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 归档客户
 **/
public class ArchiveParam {
	@NotEmpty(message = "客户id不能为空")
	private String userId; // 客户id

	public void setUserId(String userId) {
 		this.userId = userId;
	}

	public String getUserId() {
 		return userId;
	}

}
