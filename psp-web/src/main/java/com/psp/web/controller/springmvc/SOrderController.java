package com.psp.web.controller.springmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;
import com.psp.web.controller.res.*;
import com.psp.web.controller.res.bean.*;
import com.psp.web.controller.springmvc.req.*;

/**
 * 工单相关接口
 **/
@Controller
@RequestMapping(value = "/order", produces = "application/json")
public class SOrderController {
	@Autowired
	com.psp.web.controller.OrderController orderController;

	/**
	 * 按层级获取所有服务分类JSON
	 **/
	@RequestMapping("/v1/getAllServices")
	@ResponseBody
	public ObjectResult<RCategoryJSONBean> getAllService(HttpServletRequest request, HttpServletResponse response) {

		return orderController.getAllService(request, response);
	}

	/**
	 * 获取服务分类JSON
	 **/
	@RequestMapping("/v1/getCategories")
	@ResponseBody
	public ObjectResult<RCategoryJSONBean> getCategories(HttpServletRequest request, HttpServletResponse response) {

		return orderController.getCategories(request, response);
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

		return orderController.getService(param, request, response);
	}

	/**
	 * 提交工单
	 **/
	@RequestMapping("/v1/submitOrder")
	@ResponseBody
	public BaseResult submitOrder(@Validated SubmitOrderParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return orderController.submitOrder(param, request, response);
	}
}
