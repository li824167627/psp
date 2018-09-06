package com.northend.api.go.bean;

import java.util.List;

public class Bean {

	private String className;
	private String notes;
	private List<BeanAttribute> attrs;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public List<BeanAttribute> getAttrs() {
		return attrs;
	}

	public void setAttrs(List<BeanAttribute> attrs) {
		this.attrs = attrs;
	}

}
