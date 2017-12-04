package com.psp.admin.controller.springmvc.req;



/**
 * 检查登录用户名是否合法
 **/
public class CheckLoginUserParam {
	private String userName; // 登录时验证用户名，验证格式，验证是否已注册

	public void setUserName(String userName) {
 		this.userName = userName;
	}

	public String getUserName() {
 		return userName;
	}

}
