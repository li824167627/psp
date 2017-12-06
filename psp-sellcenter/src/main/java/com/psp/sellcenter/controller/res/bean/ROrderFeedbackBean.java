package com.psp.sellcenter.controller.res.bean;


/**
 * 合同反馈详情信息
 **/
public class ROrderFeedbackBean {
	private Integer fid; // fid
	private String content; // 反馈内容
	private Double averageScore; // 平均得分
	private String suggestion; // 反馈意见

	public void setFid(Integer fid) {
 		this.fid = fid;
	}

	public Integer getFid() {
 		return fid;
	}

	public void setContent(String content) {
 		this.content = content;
	}

	public String getContent() {
 		return content;
	}

	public void setAverageScore(Double averageScore) {
 		this.averageScore = averageScore;
	}

	public Double getAverageScore() {
 		return averageScore;
	}

	public void setSuggestion(String suggestion) {
 		this.suggestion = suggestion;
	}

	public String getSuggestion() {
 		return suggestion;
	}

}
