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
	@RequestMapping("/v1/getUserOrders")
	@ResponseBody
	public ListResult<ROrderBean> getUserOrders(@Validated GetUserOrdersParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ListResult<ROrderBean> res = new ListResult<ROrderBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return orderController.getUserOrders(param, request, response);
	}

	/**
	 * 新建客户工单
	 **/
	@RequestMapping("/v1/add")
	@ResponseBody
	public BaseResult add(@Validated AddOrderParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return orderController.add(param, request, response);
	}
}
