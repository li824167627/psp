package com.psp.sellcenter.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.psp.sellcenter.controller.res.BaseResult;
import com.psp.sellcenter.controller.res.ListResult;
import com.psp.sellcenter.controller.res.bean.ROrderBean;
import com.psp.sellcenter.controller.springmvc.req.AddOrderParam;
import com.psp.sellcenter.controller.springmvc.req.GetUserOrdersParam;

@Component
public class OrderController {

	public ListResult<ROrderBean> getUserOrders(GetUserOrdersParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public BaseResult add(AddOrderParam param, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

}
