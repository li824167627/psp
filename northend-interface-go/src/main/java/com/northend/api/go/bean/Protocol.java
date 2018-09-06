package com.northend.api.go.bean;

import java.util.List;

public class Protocol {

	private String name;
	private String notes;
	private int state;
	private String resType;
	private String requestMapping;
	private String requestName;
	private String responseName;
	private String responseDemoValue;
	private String resDataType;

	private List<RequestParam> request;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public List<RequestParam> getRequest() {
		return request;
	}

	public void setRequest(List<RequestParam> request) {
		this.request = request;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public String getRequestMapping() {
		return requestMapping;
	}

	public void setRequestMapping(String requestMapping) {
		this.requestMapping = requestMapping;
	}

	public String getRequestName() {
		return requestName;
	}

	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}

	public String getResponseName() {
		return responseName;
	}

	public void setResponseName(String responseName) {
		this.responseName = responseName;
	}

	public String getResDataType() {
		return resDataType;
	}

	public void setResDataType(String resDataType) {
		this.resDataType = resDataType;
	}

	public String getResponseDemoValue() {
		return responseDemoValue;
	}

	public void setResponseDemoValue(String responseDemoValue) {
		this.responseDemoValue = responseDemoValue;
	}

}
