package com.psp.sellcenter.controller.springmvc.req;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 获取工单基本信息
 **/
public class GetOrderDetailParam {
	@NotEmpty(message = "工单id不能为空")
	private String oid; // 工单ID

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getOid() {
		return oid;
	}

}
