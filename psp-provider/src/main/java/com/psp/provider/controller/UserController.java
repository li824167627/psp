package com.psp.provider.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.psp.provider.controller.res.ObjectResult;
import com.psp.provider.controller.res.bean.RUserBean;
import com.psp.provider.controller.springmvc.req.LoginParam;

@Component
public class UserController {

	public ObjectResult<RUserBean> login(LoginParam param, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

}
