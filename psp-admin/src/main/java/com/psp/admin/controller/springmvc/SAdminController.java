package com.psp.admin.controller.springmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;
import com.psp.admin.controller.res.*;
import com.psp.admin.controller.res.bean.*;
import com.psp.admin.controller.springmvc.req.*;

/**
 * 管理员相关接口
 **/
@Controller
@RequestMapping(value = "/admin", produces = "application/json")
public class SAdminController {
	@Autowired
	com.psp.admin.controller.AdminController adminController;

	/**
	 * 登录
	 **/
	@RequestMapping("/v1/login")
	@ResponseBody
	public ObjectResult<RAdminBean> login(@Validated LoginParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RAdminBean> res = new ObjectResult<RAdminBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return adminController.login(param, request, response);
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

		return adminController.sendVCode(param, request, response);
	}

	/**
	 * 找回密码验证手机
	 **/
	@RequestMapping("/v1/sendFindPwdCode")
	@ResponseBody
	public ObjectResult<RUserBean> sendFindPwdCode(@Validated SendFindPwdCodeParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RUserBean> res = new ObjectResult<RUserBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return adminController.sendFindPwdCode(param, request, response);
	}

	/**
	 * 确认找回密码验证码
	 **/
	@RequestMapping("/v1/confirmFindPwdCode")
	@ResponseBody
	public ObjectResult<RUserBean> confirmFindPwdCode(@Validated ConfirmFindPwdCodeParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RUserBean> res = new ObjectResult<RUserBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return adminController.confirmFindPwdCode(param, request, response);
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

		return adminController.resetPwd(param, request, response);
	}

	/**
	 * 获取管理员信息
	 **/
	@RequestMapping("/v1/getAdmin")
	@ResponseBody
	public ObjectResult<RAdminBean> getAdmin(@Validated GetAdminParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RAdminBean> res = new ObjectResult<RAdminBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return adminController.getAdmin(param, request, response);
	}

	/**
	 * 更新用户名
	 **/
	@RequestMapping("/v1/updateName")
	@ResponseBody
	public BaseResult updateName(@Validated UpdateNameParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return adminController.updateName(param, request, response);
	}

	/**
	 * 更改密码
	 **/
	@RequestMapping("/v1/updatePassWord")
	@ResponseBody
	public BaseResult updatePassWord(@Validated UpdatePasswordParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return adminController.updatePassWord(param, request, response);
	}

	/**
	 * 获取管理员列表
	 **/
	@RequestMapping("/v1/getList")
	@ResponseBody
	public ListResult<RAdminBean> getList(@Validated GetAdminsParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ListResult<RAdminBean> res = new ListResult<RAdminBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return adminController.getList(param, request, response);
	}

	/**
	 * 编辑管理员
	 **/
	@RequestMapping("/v1/eidt")
	@ResponseBody
	public BaseResult eidt(@Validated EditAdminParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return adminController.eidt(param, request, response);
	}

	/**
	 * 重置服务商账户密码
	 **/
	@RequestMapping("/v1/restAdminPwd")
	@ResponseBody
	public BaseResult resetAdminPwd(@Validated ResetAdminPwdParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return adminController.resetAdminPwd(param, request, response);
	}

	/**
	 * 删除管理员
	 **/
	@RequestMapping("/v1/del")
	@ResponseBody
	public BaseResult del(@Validated DelAdminParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return adminController.del(param, request, response);
	}
}
