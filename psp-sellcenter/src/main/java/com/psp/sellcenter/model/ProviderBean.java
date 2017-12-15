package com.psp.sellcenter.model;

import java.sql.Timestamp;

public class ProviderBean {
	private String pid;
	private String name;
	private String address;
	private String contact;
	private String phoneNum;
	private String content;
	private Double score;
	private Double TotalScore;
	private Integer scoreNum;
	private Timestamp createTime;
	
	private Integer type;
	
	private Integer cid;// 服务
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Double getTotalScore() {
		return TotalScore;
	}
	public void setTotalScore(Double totalScore) {
		TotalScore = totalScore;
	}
	public Integer getScoreNum() {
		return scoreNum;
	}
	public void setScoreNum(Integer scoreNum) {
		this.scoreNum = scoreNum;
	}

}
