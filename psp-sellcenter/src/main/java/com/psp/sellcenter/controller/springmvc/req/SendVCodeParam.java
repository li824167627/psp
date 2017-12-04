package com.psp.sellcenter.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 发送验证码
 **/
public class SendVCodeParam {
	@NotEmpty(message = "手机号不能为空！")
	@Pattern(regexp = "^(1)\\d{10}$", message = "手机号格式不正确！")
	private String phone; // 手机号码
	@Pattern(regexp = "^1|2|3$", message = "类型不正确，1 更新手机号2 找回密码3 重置个人密码")
	private String type; // 类型，1 更新手机号2 找回密码3 重置个人密码
	private String imgKey; // 获取时返回的key
	private String imgCode; // 图形验证码

	public void setPhone(String phone) {
 		this.phone = phone;
	}

	public String getPhone() {
 		return phone;
	}

	public void setType(String type) {
 		this.type = type;
	}

	public String getType() {
 		return type;
	}

	public void setImgKey(String imgKey) {
 		this.imgKey = imgKey;
	}

	public String getImgKey() {
 		return imgKey;
	}

	public void setImgCode(String imgCode) {
 		this.imgCode = imgCode;
	}

	public String getImgCode() {
 		return imgCode;
	}

}
