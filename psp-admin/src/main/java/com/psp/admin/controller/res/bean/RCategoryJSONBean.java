package com.psp.admin.controller.res.bean;


import com.alibaba.fastjson.JSONArray;

/**
 * 服务分类
 **/
public class RCategoryJSONBean {
	private JSONArray category; // 服务类型:cid 分类的值，name：分类标题，children：子分类，

	public void setCategory(JSONArray category) {
 		this.category = category;
	}

	public JSONArray getCategory() {
 		return category;
	}

}
