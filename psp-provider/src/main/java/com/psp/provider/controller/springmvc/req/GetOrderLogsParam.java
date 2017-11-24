package com.psp.provider.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 获取工单操作日志
 **/
public class GetOrderLogsParam {
	@NotEmpty(message = "工单id不能为空")
	private String oid; // 工单ID
	private String key; // 关键字

	public void setOid(String oid) {
 		this.oid = oid;
	}

	public String getOid() {
 		return oid;
	}

	public void setKey(String key) {
 		this.key = key;
	}

	public String getKey() {
 		return key;
	}

}
