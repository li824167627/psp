package com.psp.provider.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 申请终止工单
 **/
public class SubmitCloseParam {
	@NotEmpty(message = "工单id不能为空")
	private String oid; // 工单ID
	@Pattern(regexp = "^1|2$", message = "状态错误：1 客户终止，2其他")
	private String type; // 申请关闭状态:1 客户终止，2其他
	private String content; // 终止原因

	public void setOid(String oid) {
 		this.oid = oid;
	}

	public String getOid() {
 		return oid;
	}

	public void setType(String type) {
 		this.type = type;
	}

	public String getType() {
 		return type;
	}

	public void setContent(String content) {
 		this.content = content;
	}

	public String getContent() {
 		return content;
	}

}
