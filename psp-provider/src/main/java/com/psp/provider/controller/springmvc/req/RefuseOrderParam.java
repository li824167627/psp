package com.psp.provider.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 拒绝工单
 **/
public class RefuseOrderParam {
	@NotEmpty(message = "工单id不能为空")
	private String oid; // 工单ID
	private String content; // 拒绝理由

	public void setOid(String oid) {
 		this.oid = oid;
	}

	public String getOid() {
 		return oid;
	}

	public void setContent(String content) {
 		this.content = content;
	}

	public String getContent() {
 		return content;
	}

}
