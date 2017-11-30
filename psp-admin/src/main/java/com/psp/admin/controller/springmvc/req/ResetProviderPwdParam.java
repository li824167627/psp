package com.psp.admin.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 重置服务商账户密码
 **/
public class ResetProviderPwdParam {
	@NotEmpty(message = "服务商账号id不能为空")
	private String aid; // 服务商账号id

	public void setAid(String aid) {
 		this.aid = aid;
	}

	public String getAid() {
 		return aid;
	}

}
