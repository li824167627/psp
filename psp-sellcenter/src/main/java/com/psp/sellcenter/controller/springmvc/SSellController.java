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
 * 销售相关接口
 **/
@Controller
@RequestMapping(value = "/seller", produces = "application/json")
public class SSellController {
	@Autowired
	com.psp.sellcenter.controller.SellController sellController;

	/**
	 * 登录
	 **/
	@RequestMapping("/v1/login")
	@ResponseBody
	public ObjectResult<RSellerBean> login(@Validated LoginParam param, BindingResult error, HttpServletRequest request,
			HttpServletResponse response) {
		ObjectResult<RSellerBean> res = new ObjectResult<RSellerBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return sellController.login(param, request, response);
	}

	/**
	 * 发送验证码
	 **/
	@RequestMapping("/v1/sendVCode")
	@ResponseBody
	public BaseResult sendVCode(@Validated SendVCodeParam param, BindingResult error, HttpServletRequest request,
			HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return sellController.sendVCode(param, request, response);
	}

	/**
	 * 找回密码验证手机
	 **/
	@RequestMapping("/v1/sendFindPwdCode")
	@ResponseBody
	public ObjectResult<RSellerBean> sendFindPwdCode(@Validated SendFindPwdCodeParam param, BindingResult error,
			HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RSellerBean> res = new ObjectResult<RSellerBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return sellController.sendFindPwdCode(param, request, response);
	}

	/**
	 * 确认找回密码验证码
	 **/
	@RequestMapping("/v1/confirmFindPwdCode")
	@ResponseBody
	public ObjectResult<RSellerBean> confirmFindPwdCode(@Validated ConfirmFindPwdCodeParam param, BindingResult error,
			HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RSellerBean> res = new ObjectResult<RSellerBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return sellController.confirmFindPwdCode(param, request, response);
	}

	/**
	 * 重设密码
	 **/
	@RequestMapping("/v1/resetPwd")
	@ResponseBody
	public BaseResult resetPwd(@Validated ResetPwdParam param, BindingResult error, HttpServletRequest request,
			HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return sellController.resetPwd(param, request, response);
	}

	/**
	 * 获取销售信息
	 **/
	@RequestMapping("/v1/getSeller")
	@ResponseBody
	public ObjectResult<RSellerBean> getSeller(@Validated GetSellerParam param, BindingResult error,
			HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RSellerBean> res = new ObjectResult<RSellerBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return sellController.getSeller(param, request, response);
	}

	/**
	 * 更新用户名
	 **/
	@RequestMapping("/v1/updateName")
	@ResponseBody
	public ObjectResult<RSellerBean> updateName(@Validated UpdateNameParam param, BindingResult error,
			HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RSellerBean> res = new ObjectResult<RSellerBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return sellController.updateName(param, request, response);
	}
}
