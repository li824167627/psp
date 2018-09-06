package com.psp.sellcenter.controller.springmvc.req;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 分配工单给服务商
 **/
public class AllotOrderParam {
	@NotEmpty(message = "工单id不能为空")
	private String oid; // 工单ID
	@NotEmpty(message = "服务商不能为空")
	private String pid; // 选择服务商

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getOid() {
		return oid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPid() {
		return pid;
	}

}
