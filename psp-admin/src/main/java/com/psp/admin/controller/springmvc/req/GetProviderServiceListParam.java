package com.psp.admin.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 获取服务商列表
 **/
public class GetProviderServiceListParam {
	@NotEmpty(message = "设置服务商不能为空")
	private String pid; // 服务商id

	public void setPid(String pid) {
 		this.pid = pid;
	}

	public String getPid() {
 		return pid;
	}

}
