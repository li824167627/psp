package com.psp.sellcenter.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.psp.sellcenter.controller.res.BaseResult;
import com.psp.sellcenter.controller.res.ListResult;
import com.psp.sellcenter.controller.res.bean.RSaleUserNewsBean;
import com.psp.sellcenter.controller.springmvc.req.EditUserNewsParam;
import com.psp.sellcenter.controller.springmvc.req.GetUserNewsParam;

@Component
public class UserNewsController {

	public ListResult<RSaleUserNewsBean> getUserNews(GetUserNewsParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public BaseResult edit(EditUserNewsParam param, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

}
