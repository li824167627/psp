package com.psp.provider.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 获取服务商账号信息
 **/
public class GetAccountParam {
	@NotEmpty(message = "token不能为空")
	private String token; // 登录token

	public void setToken(String token) {
 		this.token = token;
	}

	public String getToken() {
 		return token;
	}

}
