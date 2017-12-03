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
 * 园区相关接口
 **/
@Controller
@RequestMapping(value = "/park", produces = "application/json")
public class SParkController {
	@Autowired
	com.psp.admin.controller.ParkController parkController;

	/**
	 * 获取园区列表
	 **/
	@RequestMapping("/v1/getList")
	@ResponseBody
	public ListResult<RParkBean> getList(@Validated GetParksParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ListResult<RParkBean> res = new ListResult<RParkBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return parkController.getList(param, request, response);
	}

	/**
	 * 编辑园区
	 **/
	@RequestMapping("/v1/eidt")
	@ResponseBody
	public BaseResult eidt(@Validated EditParkParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return parkController.eidt(param, request, response);
	}

	/**
	 * 删除园区
	 **/
	@RequestMapping("/v1/del")
	@ResponseBody
	public BaseResult del(@Validated DelParkParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return parkController.del(param, request, response);
	}

	/**
	 * 获取园区列表
	 **/
	@RequestMapping("/v1/getUserNum")
	@ResponseBody
	public ObjectResult<Integer> getUserNum(@Validated GetParkUserNumParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<Integer> res = new ObjectResult<Integer>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return parkController.getUserNum(param, request, response);
	}

	/**
	 * 获取园区列表
	 **/
	@RequestMapping("/v1/getOrderNum")
	@ResponseBody
	public ObjectResult<Integer> getOrderNum(@Validated GetParkOrderNumParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<Integer> res = new ObjectResult<Integer>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return parkController.getOrderNum(param, request, response);
	}
}
