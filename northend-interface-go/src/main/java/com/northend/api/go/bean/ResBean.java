package com.northend.api.go.bean;

public class ResBean {

	private String resCode;
	private String resMsg;
	private String resData;
	private boolean toCreate;

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public String getResData() {
		return resData;
	}

	public void setResData(String resData) {
		this.resData = resData;
	}

	public boolean isToCreate() {
		return toCreate;
	}

	public void setToCreate(boolean toCreate) {
		this.toCreate = toCreate;
	}

}
