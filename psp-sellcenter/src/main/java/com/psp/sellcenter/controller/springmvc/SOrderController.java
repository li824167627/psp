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
 * 工单相关接口
 **/
@Controller
@RequestMapping(value = "/order", produces = "application/json")
public class SOrderController {
	@Autowired
	com.psp.sellcenter.controller.OrderController orderController;

	/**
	 * 获取客户工单列表
	 **/
	@RequestMapping("/v1/getOrders")
	@ResponseBody
	public ListResult<ROrderBean> getOrders(@Validated GetOrdersParam param, BindingResult error,
			HttpServletRequest request, HttpServletResponse response) {
		ListResult<ROrderBean> res = new ListResult<ROrderBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return orderController.getOrders(param, request, response);
	}

	/**
	 * 获取工单数量
	 **/
	@RequestMapping("/v1/getOrderNum")
	@ResponseBody
	public ObjectResult<Integer> getOrderNum(@Validated GetOrderNumParam param, BindingResult error,
			HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<Integer> res = new ObjectResult<Integer>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return orderController.getOrderNum(param, request, response);
	}

	/**
	 * 新建客户工单
	 **/
	@RequestMapping("/v1/add")
	@ResponseBody
	public BaseResult add(@Validated AddOrderParam param, BindingResult error, HttpServletRequest request,
			HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return orderController.add(param, request, response);
	}

	/**
	 * 获取服务分类及服务商列表
	 **/
	@RequestMapping("/v1/getServiceProviders")
	@ResponseBody
	public ObjectResult<RServiceProviderBean> getServiceProviders(HttpServletRequest request,
			HttpServletResponse response) {

		return orderController.getServiceProviders(request, response);
	}

	/**
	 * 获取工单基本信息
	 **/
	@RequestMapping("/v1/getDetail")
	@ResponseBody
	public ObjectResult<ROrderBean> getDetail(@Validated GetOrderDetailParam param, BindingResult error,
			HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<ROrderBean> res = new ObjectResult<ROrderBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return orderController.getDetail(param, request, response);
	}

	/**
	 * 获取工单操作日志
	 **/
	@RequestMapping("/v1/getOrderLogs")
	@ResponseBody
	public ListResult<ROrderLogsBean> getOrderLogs(@Validated GetOrderLogsParam param, BindingResult error,
			HttpServletRequest request, HttpServletResponse response) {
		ListResult<ROrderLogsBean> res = new ListResult<ROrderLogsBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return orderController.getOrderLogs(param, request, response);
	}

	/**
	 * 分配工单给服务商
	 **/
	@RequestMapping("/v1/allotOrder")
	@ResponseBody
	public BaseResult allotOrder(@Validated AllotOrderParam param, BindingResult error, HttpServletRequest request,
			HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return orderController.allotOrder(param, request, response);
	}

	/**
	 * 关闭工单
	 **/
	@RequestMapping("/v1/closeOrder")
	@ResponseBody
	public BaseResult closeOrder(@Validated CloseOrderParam param, BindingResult error, HttpServletRequest request,
			HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return orderController.closeOrder(param, request, response);
	}

	/**
	 * 上传合同
	 **/
	@RequestMapping("/v1/uploadContract")
	@ResponseBody
	public BaseResult uploadContract(@Validated UploadContractParam param, BindingResult error,
			HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return orderController.uploadContract(param, request, response);
	}

	/**
	 * 确认工单
	 **/
	@RequestMapping("/v1/confirmOrder")
	@ResponseBody
	public BaseResult confirmOrder(@Validated ConfirmOrderParam param, BindingResult error, HttpServletRequest request,
			HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return orderController.confirmOrder(param, request, response);
	}

	/**
	 * 工单反馈
	 **/
	@RequestMapping("/v1/feedback")
	@ResponseBody
	public BaseResult feedback(@Validated FeedbackParam param, BindingResult error, HttpServletRequest request,
			HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return orderController.feedback(param, request, response);
	}
}
