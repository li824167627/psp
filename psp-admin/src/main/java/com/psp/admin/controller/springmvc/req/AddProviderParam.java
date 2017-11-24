package com.psp.admin.controller.springmvc.req;



/**
 * 创建服务商并生成管理员账户
 **/
public class AddProviderParam {
	private String name; // 页码，默认从0开始

	public void setName(String name) {
 		this.name = name;
	}

	public String getName() {
 		return name;
	}

}
