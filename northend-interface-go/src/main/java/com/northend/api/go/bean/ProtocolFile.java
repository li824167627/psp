package com.northend.api.go.bean;

import java.util.List;

/**
 * 协议文件
 * 
 * @author cuihaidong
 *
 */
public class ProtocolFile {

	private String className;
	private String fileName;

	private boolean toCreate = false;
	private String version;
	private String notes;

	private String requestMapping;
	private List<Protocol> protocols;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean isToCreate() {
		return toCreate;
	}

	public void setToCreate(boolean toCreate) {
		this.toCreate = toCreate;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public List<Protocol> getProtocols() {
		return protocols;
	}

	public void setProtocols(List<Protocol> protocols) {
		this.protocols = protocols;
	}

	public String getRequestMapping() {
		return requestMapping;
	}

	public void setRequestMapping(String requestMapping) {
		this.requestMapping = requestMapping;
	}

}
