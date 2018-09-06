package com.psp.sellcenter.controller.springmvc.req;

/**
 * 文件-获取图片验证码
 **/
public class getImgcodeParam {
	private String w; // 验证码的宽
	private String h; // 验证码的高

	public void setW(String w) {
		this.w = w;
	}

	public String getW() {
		return w;
	}

	public void setH(String h) {
		this.h = h;
	}

	public String getH() {
		return h;
	}

}
