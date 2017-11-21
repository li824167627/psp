package com.psp.sellcenter.controller.res.bean;


/**
 * 客户信息流信息（预工单）
 **/
public class RSaleUserNewsBean {
	private String nid; // 客户信息id
	private String label; // 消息标签
	private String content; // 需求描述
	private String uid; // 用户
	private Integer origin; // 来源：0线上（PC） 1 主动沟通
	private Long createTime; // 创建时间
	private Long updateTime; // 更新时间

	public void setNid(String nid) {
 		this.nid = nid;
	}

	public String getNid() {
 		return nid;
	}

	public void setLabel(String label) {
 		this.label = label;
	}

	public String getLabel() {
 		return label;
	}

	public void setContent(String content) {
 		this.content = content;
	}

	public String getContent() {
 		return content;
	}

	public void setUid(String uid) {
 		this.uid = uid;
	}

	public String getUid() {
 		return uid;
	}

	public void setOrigin(Integer origin) {
 		this.origin = origin;
	}

	public Integer getOrigin() {
 		return origin;
	}

	public void setCreateTime(Long createTime) {
 		this.createTime = createTime;
	}

	public Long getCreateTime() {
 		return createTime;
	}

	public void setUpdateTime(Long updateTime) {
 		this.updateTime = updateTime;
	}

	public Long getUpdateTime() {
 		return updateTime;
	}

}
