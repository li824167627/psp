package com.psp.admin.controller.res.bean;


import com.alibaba.fastjson.JSONArray;

/**
 * 区域列表bean
 **/
public class RAreaListBean {
	private JSONArray area; // 服务类型:cid 分类的值，name：分类标题，children：子分类，

	public void setArea(JSONArray area) {
 		this.area = area;
	}

	public JSONArray getArea() {
 		return area;
	}

}
