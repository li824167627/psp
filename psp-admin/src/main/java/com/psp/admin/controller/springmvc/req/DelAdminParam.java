package com.psp.admin.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 删除管理员
 **/
public class DelAdminParam {
	@NotEmpty(message = "管理员id不能为空")
	private String aid; // 管理员id

	public void setAid(String aid) {
 		this.aid = aid;
	}

	public String getAid() {
 		return aid;
	}

}
