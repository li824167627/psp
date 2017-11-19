package com.psp.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.psp.admin.controller.res.ObjectResult;
import com.psp.admin.controller.res.bean.RUserBean;
import com.psp.admin.controller.springmvc.req.LoginParam;

@Component
public class UserController {

	public ObjectResult<RUserBean> login(LoginParam param, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

}
