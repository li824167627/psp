package com.psp.admin.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 获取管理员信息
 **/
public class GetAdminParam {
	@NotEmpty(message = "token不能为空")
	private String token; // 登录token

	public void setToken(String token) {
 		this.token = token;
	}

	public String getToken() {
 		return token;
	}

}
