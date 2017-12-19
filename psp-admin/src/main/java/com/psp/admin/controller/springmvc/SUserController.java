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
 * 客户相关接口
 **/
@Controller
@RequestMapping(value = "/user", produces = "application/json")
public class SUserController {
	@Autowired
	com.psp.admin.controller.UserController userController;

	/**
	 * 获取客户列表
	 **/
	@RequestMapping("/v1/getUsers")
	@ResponseBody
	public ListResult<RUserBean> getUsers(@Validated GetUsersParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ListResult<RUserBean> res = new ListResult<RUserBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return userController.getUsers(param, request, response);
	}

	/**
	 * 获取客户数量
	 **/
	@RequestMapping("/v1/getUserNum")
	@ResponseBody
	public ObjectResult<Integer> getUserNum(@Validated GetUserNumParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<Integer> res = new ObjectResult<Integer>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return userController.getUserNum(param, request, response);
	}

	/**
	 * 分配客户给销售
	 **/
	@RequestMapping("/v1/allot")
	@ResponseBody
	public BaseResult allot(@Validated AllotParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return userController.allot(param, request, response);
	}

	/**
	 * 获取客户基本信息
	 **/
	@RequestMapping("/v1/getDetail")
	@ResponseBody
	public ObjectResult<RUserBean> getDetail(@Validated GetUserDetailParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RUserBean> res = new ObjectResult<RUserBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return userController.getDetail(param, request, response);
	}

	/**
	 * 获取客户操作日志
	 **/
	@RequestMapping("/v1/getUserLogs")
	@ResponseBody
	public ListResult<RUserLogsBean> getUserLogs(@Validated GetUserLogsParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ListResult<RUserLogsBean> res = new ListResult<RUserLogsBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return userController.getUserLogs(param, request, response);
	}

	/**
	 * 获取客户信息流
	 **/
	@RequestMapping("/v1/getUserNews")
	@ResponseBody
	public ListResult<RUserNewsBean> getUserNews(@Validated GetUserNewsParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ListResult<RUserNewsBean> res = new ListResult<RUserNewsBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return userController.getUserNews(param, request, response);
	}

	/**
	 * 文件-导入客户信息
	 **/
	@RequestMapping("/v1/improtUsers")
	@ResponseBody
	public BaseResult ImportUsers(HttpServletRequest request, HttpServletResponse response) {

		return userController.ImportUsers(request, response);
	}
}
