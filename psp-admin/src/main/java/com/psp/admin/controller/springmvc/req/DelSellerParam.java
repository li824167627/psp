package com.psp.admin.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 删除销售账户
 **/
public class DelSellerParam {
	@NotEmpty(message = "销售账号id不能为空")
	private String sid; // 销售账号id

	public void setSid(String sid) {
 		this.sid = sid;
	}

	public String getSid() {
 		return sid;
	}

}
