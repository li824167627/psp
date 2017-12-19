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
 * 工单相关接口
 **/
@Controller
@RequestMapping(value = "/order", produces = "application/json")
public class SOrderController {
	@Autowired
	com.psp.admin.controller.OrderController orderController;

	/**
	 * 获取客户工单列表
	 **/
	@RequestMapping("/v1/getOrders")
	@ResponseBody
	public ListResult<ROrderBean> getOrders(@Validated GetOrdersParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ListResult<ROrderBean> res = new ListResult<ROrderBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return orderController.getOrders(param, request, response);
	}

	/**
	 * 获取工单基本信息
	 **/
	@RequestMapping("/v1/getDetail")
	@ResponseBody
	public ObjectResult<ROrderBean> getDetail(@Validated GetOrderDetailParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<ROrderBean> res = new ObjectResult<ROrderBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return orderController.getDetail(param, request, response);
	}

	/**
	 * 获取工单数量
	 **/
	@RequestMapping("/v1/getOrderNum")
	@ResponseBody
	public ObjectResult<Integer> getOrderNum(@Validated GetOrderNumParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<Integer> res = new ObjectResult<Integer>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return orderController.getOrderNum(param, request, response);
	}

	/**
	 * 获取工单操作日志
	 **/
	@RequestMapping("/v1/getOrderLogs")
	@ResponseBody
	public ListResult<ROrderLogsBean> getOrderLogs(@Validated GetOrderLogsParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ListResult<ROrderLogsBean> res = new ListResult<ROrderLogsBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return orderController.getOrderLogs(param, request, response);
	}

	/**
	 * 文件-上传文件
	 **/
	@RequestMapping("/v1/improtOrders")
	@ResponseBody
	public BaseResult ImportOrders(HttpServletRequest request, HttpServletResponse response) {

		return orderController.ImportOrders(request, response);
	}
}
