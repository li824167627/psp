package com.psp.admin.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 删除园区
 **/
public class DelParkParam {
	@NotEmpty(message = "园区id不能为空")
	private String pid; // 园区id

	public void setPid(String pid) {
 		this.pid = pid;
	}

	public String getPid() {
 		return pid;
	}

}
