package com.psp.sellcenter.controller.springmvc.req;


import javax.validation.constraints.Pattern;

/**
 * 获取客户数量
 **/
public class GetUserNumParam {
	@Pattern(regexp = "^0|1$", message = "状态错误：0:全部1:待沟通客户")
	private String status; // 搜索客户状态，0:全部1:待沟通客户

	public void setStatus(String status) {
 		this.status = status;
	}

	public String getStatus() {
 		return status;
	}

}
