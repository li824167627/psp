package com.psp.provider.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.psp.provider.controller.res.BaseResult;
import com.psp.provider.controller.res.ObjectResult;
import com.psp.provider.controller.res.bean.RImgCodeBean;
import com.psp.provider.controller.res.bean.RQiniuFileBean;
import com.psp.provider.controller.springmvc.req.getImgcodeParam;
import com.psp.provider.controller.springmvc.req.getLoginImgcodeParam;
@Component
public class FileController {

	public ObjectResult<String> getQINIUToken(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectResult<RQiniuFileBean> uploadImage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public BaseResult getLoginImgCode(getLoginImgcodeParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectResult<RImgCodeBean> getImgCode(getImgcodeParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

}
