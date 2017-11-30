package com.psp.admin.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 删除服务商
 **/
public class DelProviderParam {
	@NotEmpty(message = "服务商id不能为空")
	private String pid; // 服务商id

	public void setPid(String pid) {
 		this.pid = pid;
	}

	public String getPid() {
 		return pid;
	}

}
