package com.psp.sellcenter.controller.springmvc.req;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 工单反馈
 **/
public class FeedbackParam {
	@NotEmpty(message = "工单id不能为空")
	private String oid; // 工单ID
	private String score; // 服务质量，服务速度，服务态度的评分
	private String content; // 评价描述

	public void setOid(String oid) {
 		this.oid = oid;
	}

	public String getOid() {
 		return oid;
	}

	public void setScore(String score) {
 		this.score = score;
	}

	public String getScore() {
 		return score;
	}

	public void setContent(String content) {
 		this.content = content;
	}

	public String getContent() {
 		return content;
	}

}
