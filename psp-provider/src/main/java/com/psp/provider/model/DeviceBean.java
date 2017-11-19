package com.psp.provider.model;

/**
 * 设备明细
 * 
 * @author cuihaidong
 *
 */
public class DeviceBean {

	private String appName;// 浏览器名称
	private String appCodeName;// 浏览器代码名
	private String appVersion;// 浏览器版本号
	private String platform;// 平台系统

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppCodeName() {
		return appCodeName;
	}

	public void setAppCodeName(String appCodeName) {
		this.appCodeName = appCodeName;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

}
