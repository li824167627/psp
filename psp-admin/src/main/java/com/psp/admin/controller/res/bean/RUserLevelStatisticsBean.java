package com.psp.admin.controller.res.bean;


/**
 * 客户级别数量信息
 **/
public class RUserLevelStatisticsBean {
	private Integer unrated; // 未定级
	private Integer valid; // 有效
	private Integer unvalid; // 无效

	public void setUnrated(Integer unrated) {
 		this.unrated = unrated;
	}

	public Integer getUnrated() {
 		return unrated;
	}

	public void setValid(Integer valid) {
 		this.valid = valid;
	}

	public Integer getValid() {
 		return valid;
	}

	public void setUnvalid(Integer unvalid) {
 		this.unvalid = unvalid;
	}

	public Integer getUnvalid() {
 		return unvalid;
	}

}
