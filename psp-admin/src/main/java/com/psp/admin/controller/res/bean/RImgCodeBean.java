package com.psp.admin.controller.res.bean;


/**
 * 图片验证码
 **/
public class RImgCodeBean {
	private String key; // key
	private String url; // 图片url地址
	private Integer imgH; // 高度
	private Integer imgW; // 宽度
	private String format; // 文件格式

	public void setKey(String key) {
 		this.key = key;
	}

	public String getKey() {
 		return key;
	}

	public void setUrl(String url) {
 		this.url = url;
	}

	public String getUrl() {
 		return url;
	}

	public void setImgH(Integer imgH) {
 		this.imgH = imgH;
	}

	public Integer getImgH() {
 		return imgH;
	}

	public void setImgW(Integer imgW) {
 		this.imgW = imgW;
	}

	public Integer getImgW() {
 		return imgW;
	}

	public void setFormat(String format) {
 		this.format = format;
	}

	public String getFormat() {
 		return format;
	}

}
