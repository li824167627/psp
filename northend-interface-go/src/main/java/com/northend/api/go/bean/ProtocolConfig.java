package com.northend.api.go.bean;

import java.util.List;

/**
 * 协议配置
 * 
 * @author cuihaidong
 *
 */
public class ProtocolConfig {

	private String packageName;
	private String distPath;
	private String xmlPath;
	private String resPackageName;
	private String reqPackageName;

	private List<ProtocolFile> protocolFiles;
	private List<BeanFile> beanFiles;
	private RescodeFile rescodeFile;

	private ResBean resBean;

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public List<ProtocolFile> getProtocolFiles() {
		return protocolFiles;
	}

	public void setProtocolFiles(List<ProtocolFile> protocolFiles) {
		this.protocolFiles = protocolFiles;
	}

	public List<BeanFile> getBeanFiles() {
		return beanFiles;
	}

	public void setBeanFiles(List<BeanFile> beanFiles) {
		this.beanFiles = beanFiles;
	}

	public RescodeFile getRescodeFile() {
		return rescodeFile;
	}

	public void setRescodeFile(RescodeFile rescodeFile) {
		this.rescodeFile = rescodeFile;
	}

	public String getDistPath() {
		return distPath;
	}

	public void setDistPath(String distPath) {
		this.distPath = distPath;
	}

	public String getXmlPath() {
		return xmlPath;
	}

	public void setXmlPath(String xmlPath) {
		this.xmlPath = xmlPath;
	}

	public ResBean getResBean() {
		return resBean;
	}

	public void setResBean(ResBean resBean) {
		this.resBean = resBean;
	}

	public String getResPackageName() {
		return resPackageName;
	}

	public void setResPackageName(String resPackageName) {
		this.resPackageName = resPackageName;
	}

	public String getReqPackageName() {
		return reqPackageName;
	}

	public void setReqPackageName(String reqPackageName) {
		this.reqPackageName = reqPackageName;
	}

}
