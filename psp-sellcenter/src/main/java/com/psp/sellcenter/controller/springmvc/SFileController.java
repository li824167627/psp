package com.psp.sellcenter.controller.springmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;
import com.psp.sellcenter.controller.res.*;
import com.psp.sellcenter.controller.res.bean.*;
import com.psp.sellcenter.controller.springmvc.req.*;

/**
 * 文件相关接口
 **/
@Controller
@RequestMapping(value = "/file", produces = "application/json")
public class SFileController {
	@Autowired
	com.psp.sellcenter.controller.FileController fileController;

	/**
	 * 文件-获取七牛token
	 **/
	@RequestMapping("/v1/getQINIUToken")
	@ResponseBody
	public ObjectResult<String> getQINIUToken(HttpServletRequest request, HttpServletResponse response) {

		return fileController.getQINIUToken(request, response);
	}

	/**
	 * 文件-上传文件
	 **/
	@RequestMapping("/v1/uploadImage")
	@ResponseBody
	public ObjectResult<RQiniuFileBean> uploadImage(HttpServletRequest request, HttpServletResponse response) {

		return fileController.uploadImage(request, response);
	}

	/**
	 * 文件-获取图片验证码
	 **/
	@RequestMapping("/v1/getImgCode")
	@ResponseBody
	public ObjectResult<RImgCodeBean> getImgCode(@Validated getImgcodeParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RImgCodeBean> res = new ObjectResult<RImgCodeBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return fileController.getImgCode(param, request, response);
	}

	/**
	 * 文件-获取登录时图片验证码
	 **/
	@RequestMapping("/v1/getLoginImgCode")
	@ResponseBody
	public BaseResult getLoginImgCode(@Validated getLoginImgcodeParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return fileController.getLoginImgCode(param, request, response);
	}
}
