package com.psp.admin.controller.res.bean;


import com.alibaba.fastjson.JSONArray;

/**
 * 工单统计信息
 **/
public class ROrderStatisticsBean {
	private JSONArray status; // 工单统计状态数量信息
	private JSONArray stage; // 工单统计阶段数量信息

	public void setStatus(JSONArray status) {
 		this.status = status;
	}

	public JSONArray getStatus() {
 		return status;
	}

	public void setStage(JSONArray stage) {
 		this.stage = stage;
	}

	public JSONArray getStage() {
 		return stage;
	}

}
