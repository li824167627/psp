package com.psp.provider.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.psp.provider.controller.res.ListResult;
import com.psp.provider.controller.res.ObjectResult;
import com.psp.provider.controller.res.bean.ROrderBean;
import com.psp.provider.controller.res.bean.ROrderLogsBean;
import com.psp.provider.controller.springmvc.req.GetOrderDetailParam;
import com.psp.provider.controller.springmvc.req.GetOrderLogsParam;
import com.psp.provider.controller.springmvc.req.GetOrderNumParam;
import com.psp.provider.controller.springmvc.req.GetOrdersParam;
import com.psp.provider.service.OrderService;

@Component
public class OrderController {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	OrderService orderServiceImpl;

	public ListResult<ROrderBean> getOrders(GetOrdersParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectResult<Integer> getOrderNum(GetOrderNumParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectResult<ROrderBean> getDetail(GetOrderDetailParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public ListResult<ROrderLogsBean> getOrderLogs(GetOrderLogsParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

}
