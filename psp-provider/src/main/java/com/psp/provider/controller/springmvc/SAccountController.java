package com.psp.provider.controller.springmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;
import com.psp.provider.controller.res.*;
import com.psp.provider.controller.res.bean.*;
import com.psp.provider.controller.springmvc.req.*;

/**
 * 账户相关接口
 **/
@Controller
@RequestMapping(value = "/account", produces = "application/json")
public class SAccountController {
	@Autowired
	com.psp.provider.controller.AccountController accountController;

	/**
	 * 登录
	 **/
	@RequestMapping("/v1/login")
	@ResponseBody
	public ObjectResult<RAccountBean> login(@Validated LoginParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RAccountBean> res = new ObjectResult<RAccountBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return accountController.login(param, request, response);
	}

	/**
	 * 发送验证码
	 **/
	@RequestMapping("/v1/sendVCode")
	@ResponseBody
	public BaseResult sendVCode(@Validated SendVCodeParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return accountController.sendVCode(param, request, response);
	}

	/**
	 * 找回密码验证手机
	 **/
	@RequestMapping("/v1/sendFindPwdCode")
	@ResponseBody
	public ObjectResult<RAccountBean> sendFindPwdCode(@Validated SendFindPwdCodeParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RAccountBean> res = new ObjectResult<RAccountBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return accountController.sendFindPwdCode(param, request, response);
	}

	/**
	 * 确认找回密码验证码
	 **/
	@RequestMapping("/v1/confirmFindPwdCode")
	@ResponseBody
	public ObjectResult<RAccountBean> confirmFindPwdCode(@Validated ConfirmFindPwdCodeParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RAccountBean> res = new ObjectResult<RAccountBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return accountController.confirmFindPwdCode(param, request, response);
	}

	/**
	 * 重设密码
	 **/
	@RequestMapping("/v1/resetPwd")
	@ResponseBody
	public BaseResult resetPwd(@Validated ResetPwdParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return accountController.resetPwd(param, request, response);
	}

	/**
	 * 获取服务商账号信息
	 **/
	@RequestMapping("/v1/getAccount")
	@ResponseBody
	public ObjectResult<RAccountBean> getAccount(@Validated GetAccountParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RAccountBean> res = new ObjectResult<RAccountBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return accountController.getAccount(param, request, response);
	}

	/**
	 * 更新用户名
	 **/
	@RequestMapping("/v1/updateName")
	@ResponseBody
	public ObjectResult<RAccountBean> updateName(@Validated UpdateAccountNameParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RAccountBean> res = new ObjectResult<RAccountBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return accountController.updateName(param, request, response);
	}

	/**
	 * 获取服务商账户列表
	 **/
	@RequestMapping("/v1/getAccountList")
	@ResponseBody
	public ListResult<RAccountBean> getAccountList(@Validated GetProviderAccountListParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ListResult<RAccountBean> res = new ListResult<RAccountBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return accountController.getAccountList(param, request, response);
	}

	/**
	 * 创建服务商账户
	 **/
	@RequestMapping("/v1/addAccount")
	@ResponseBody
	public BaseResult addAccount(@Validated AddProviderAccountParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return accountController.addAccount(param, request, response);
	}

	/**
	 * 重置服务商账户密码
	 **/
	@RequestMapping("/v1/resetAccountPwd")
	@ResponseBody
	public BaseResult resetAccountPwd(@Validated ResetProviderPwdParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return accountController.resetAccountPwd(param, request, response);
	}

	/**
	 * 删除服务商账户
	 **/
	@RequestMapping("/v1/delAccount")
	@ResponseBody
	public BaseResult delAccount(@Validated DelProviderAccountParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return accountController.delAccount(param, request, response);
	}
}
