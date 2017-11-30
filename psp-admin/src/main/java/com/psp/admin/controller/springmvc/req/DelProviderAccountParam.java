package com.psp.admin.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 删除服务商账户
 **/
public class DelProviderAccountParam {
	@NotEmpty(message = "服务商账号id不能为空")
	private String aid; // 服务商账号id

	public void setAid(String aid) {
 		this.aid = aid;
	}

	public String getAid() {
 		return aid;
	}

}
