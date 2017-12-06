package com.psp.web.model;

import java.util.ArrayList;
public class CategoryTree {
	private String name;
	private String cid;
	private ArrayList<CategoryTree> children = new ArrayList<CategoryTree>();
	
	public CategoryTree(String name, String cid) {
		this.name = name;
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public ArrayList<CategoryTree> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<CategoryTree> children) {
		this.children = children;
	}

}