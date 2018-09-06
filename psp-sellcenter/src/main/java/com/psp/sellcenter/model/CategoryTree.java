package com.psp.sellcenter.model;

import java.util.ArrayList;
import java.util.List;

public class CategoryTree {
	private String name;
	private String cid;
	private String phone;
	private ArrayList<CategoryTree> children = new ArrayList<CategoryTree>();

	public CategoryTree(String name, String cid, String phone) {
		this.name = name;
		this.cid = cid;
		this.phone = phone;
	}

	public CategoryTree(String name, int cid, List<ProviderBean> lists) {
		this.name = name;
		this.cid = cid + "";
		if (lists != null && lists.size() > 0) {
			ArrayList<CategoryTree> ptrees = new ArrayList<CategoryTree>();
			for (ProviderBean p : lists) {
				CategoryTree pt = new CategoryTree(p.getName(), p.getPid(), p.getPhoneNum());
				ptrees.add(pt);
			}
			this.children = ptrees;
		}
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public ArrayList<CategoryTree> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<CategoryTree> children) {
		this.children = children;
	}

}