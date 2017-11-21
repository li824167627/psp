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
 * 客户相关接口
 **/
@Controller
@RequestMapping(value = "/user", produces = "application/json")
public class SUserController {
	@Autowired
	com.psp.sellcenter.controller.UserController userController;

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
	 * 新建客户
	 **/
	@RequestMapping("/v1/add")
	@ResponseBody
	public ObjectResult<RUserBean> add(@Validated AddUserParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RUserBean> res = new ObjectResult<RUserBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return userController.add(param, request, response);
	}

	/**
	 * 编辑客户
	 **/
	@RequestMapping("/v1/edit")
	@ResponseBody
	public ObjectResult<RUserBean> edit(@Validated EditUserParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RUserBean> res = new ObjectResult<RUserBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return userController.edit(param, request, response);
	}

	/**
	 * 编辑客户级别
	 **/
	@RequestMapping("/v1/editLevel")
	@ResponseBody
	public BaseResult editLevel(@Validated EditUserLevelParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return userController.editLevel(param, request, response);
	}

	/**
	 * 归档客户
	 **/
	@RequestMapping("/v1/archive")
	@ResponseBody
	public BaseResult archive(@Validated ArchiveParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return userController.archive(param, request, response);
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
}
