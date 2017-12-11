package com.psp.admin.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 添加服务商服务
 **/
public class AddProviderServiceParam {
	@NotEmpty(message = "设置服务商不能为空")
	private String pid; // 服务商id
	private String cid; // 所选分类

	public void setPid(String pid) {
 		this.pid = pid;
	}

	public String getPid() {
 		return pid;
	}

	public void setCid(String cid) {
 		this.cid = cid;
	}

	public String getCid() {
 		return cid;
	}

}
