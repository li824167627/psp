package com.psp.admin.service;

import javax.servlet.http.HttpServletRequest;

import com.psp.admin.controller.res.bean.RQiniuFileBean;

/**
 * 七牛的service
 * 
 *
 */
public interface QiniuService {
	/**
	 * 获取用户上传的token
	 * 
	 * @return
	 */
	String getQNToken();

	/**
	 * 上传图片到七牛
	 * @param request
	 * @return
	 */
	RQiniuFileBean uploadImgFile(HttpServletRequest request);
}
