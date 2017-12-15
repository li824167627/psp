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
 * 服务分类相关接口
 **/
@Controller
@RequestMapping(value = "/service", produces = "application/json")
public class SServiceController {
	@Autowired
	com.psp.admin.controller.ServiceController serviceController;

	/**
	 * 按层级获取所有服务分类JSON
	 **/
	@RequestMapping("/v1/getAllServices")
	@ResponseBody
	public ObjectResult<RCategoryJSONBean> getAllService(HttpServletRequest request, HttpServletResponse response) {

		return serviceController.getAllService(request, response);
	}

	/**
	 * 获取服务分类JSON
	 **/
	@RequestMapping("/v1/getCategories")
	@ResponseBody
	public ObjectResult<RCategoryJSONBean> getCategories(HttpServletRequest request, HttpServletResponse response) {

		return serviceController.getCategories(request, response);
	}

	/**
	 * 获取服务列表
	 **/
	@RequestMapping("/v1/getService")
	@ResponseBody
	public ListResult<RCategoryBean> getService(@Validated GetServiceParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ListResult<RCategoryBean> res = new ListResult<RCategoryBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return serviceController.getService(param, request, response);
	}

	/**
	 * 创建服务分类
	 **/
	@RequestMapping("/v1/addService")
	@ResponseBody
	public BaseResult addService(@Validated AddServiceParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return serviceController.addService(param, request, response);
	}

	/**
	 * 编辑服务
	 **/
	@RequestMapping("/v1/editService")
	@ResponseBody
	public BaseResult editService(@Validated EditServiceParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return serviceController.editService(param, request, response);
	}

	/**
	 * 删除服务
	 **/
	@RequestMapping("/v1/delService")
	@ResponseBody
	public BaseResult delService(@Validated DelServiceParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return serviceController.delService(param, request, response);
	}
}
