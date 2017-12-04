package com.psp.admin.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 文件-获取登录时图片验证码
 **/
public class getLoginImgcodeParam {
	@NotEmpty(message = "用户id不能为空")
	private String userId; // 登陆用户id
	private String w; // 验证码的宽
	private String h; // 验证码的高

	public void setUserId(String userId) {
 		this.userId = userId;
	}

	public String getUserId() {
 		return userId;
	}

	public void setW(String w) {
 		this.w = w;
	}

	public String getW() {
 		return w;
	}

	public void setH(String h) {
 		this.h = h;
	}

	public String getH() {
 		return h;
	}

}
