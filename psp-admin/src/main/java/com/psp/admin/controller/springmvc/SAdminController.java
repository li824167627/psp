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
		res.setData(new RAdminBean().getDemoValue());
		return res;
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
		res.setData(new RAdminBean().getDemoValue());
		return res;
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
		res.setMsg(null);
		return res;
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
		res.setMsg(null);
		return res;
	}
}