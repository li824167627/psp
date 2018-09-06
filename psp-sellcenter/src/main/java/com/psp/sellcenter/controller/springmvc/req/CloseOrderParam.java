package com.psp.sellcenter.controller.springmvc.req;

import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 关闭工单
 **/
public class CloseOrderParam {
	@NotEmpty(message = "工单id不能为空")
	private String oid; // 工单ID
	@Pattern(regexp = "^-1|-2|-3$", message = "关闭状态错误：-1不能分配-2不能生成合同-3项目终止")
	private String status; // 关闭状态：-1不能分配-2不能生成合同-3项目终止
	private String content; // 关闭描述JSON

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getOid() {
		return oid;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

}
