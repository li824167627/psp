package com.psp.admin.controller.res.bean;

import java.util.List;
import java.util.ArrayList;

/**
 * 区域列表bean
 **/
public class RAreaListBean {
	private String label; // 区域名称
	private String value; // 区域code
	private List<RAreaListBean> children; // 下级区域

	public void setLabel(String label) {
 		this.label = label;
	}

	public String getLabel() {
 		return label;
	}

	public void setValue(String value) {
 		this.value = value;
	}

	public String getValue() {
 		return value;
	}

	public void setChildren(List<RAreaListBean> children) {
 		this.children = children;
	}

	public List<RAreaListBean> getChildren() {
 		return children;
	}

}
