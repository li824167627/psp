package com.psp.sellcenter.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.psp.sellcenter.controller.res.BaseResult;
import com.psp.sellcenter.controller.res.ObjectResult;
import com.psp.sellcenter.controller.res.bean.RImgCodeBean;
import com.psp.sellcenter.controller.res.bean.RQiniuFileBean;
import com.psp.sellcenter.controller.springmvc.req.getImgcodeParam;
import com.psp.sellcenter.controller.springmvc.req.getLoginImgcodeParam;
@Component
public class FileController {

	Logger logger = Logger.getLogger(this.getClass());
	public ObjectResult<String> getQINIUToken(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectResult<RQiniuFileBean> uploadImage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectResult<RImgCodeBean> getImgCode(getImgcodeParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public BaseResult getLoginImgCode(getLoginImgcodeParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

}
