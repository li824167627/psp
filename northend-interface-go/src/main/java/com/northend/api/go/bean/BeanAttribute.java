package com.northend.api.go.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class BeanAttribute {
	private String type;
	private String name;
	private String defValue;
	private String notes;
	private String demoValue;
	private boolean isCustom;
	private String objName;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDefValue() {
		return defValue;
	}

	public void setDefValue(String defValue) {
		this.defValue = defValue;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getDemoValue() {
		return demoValue;
	}

	public void setDemoValue(String demoValue) {
		this.demoValue = demoValue;
	}

	@JSONField(name = "isCustom")
	public boolean isCustom() {
		return isCustom;
	}

	@JacksonXmlProperty(localName = "isCustom")
	public void setCustom(boolean isCustom) {
		this.isCustom = isCustom;
	}

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}

}
