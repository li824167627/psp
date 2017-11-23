package com.psp.sellcenter.controller.res.bean;


import com.alibaba.fastjson.JSONArray;

/**
 * 服务分类及服务商
 **/
public class RServiceProviderBean {
	private JSONArray service; // 服务类型:value 分类的值，title：分类标题，sort：排序，subMenus：子分类，

	public void setService(JSONArray service) {
 		this.service = service;
	}

	public JSONArray getService() {
 		return service;
	}

}
