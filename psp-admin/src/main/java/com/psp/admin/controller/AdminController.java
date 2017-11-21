package com.psp.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.psp.admin.controller.res.BaseResult;
import com.psp.admin.controller.res.ObjectResult;
import com.psp.admin.controller.res.bean.RAdminBean;
import com.psp.admin.controller.springmvc.req.GetAdminParam;
import com.psp.admin.controller.springmvc.req.LoginParam;
import com.psp.admin.controller.springmvc.req.UpdateNameParam;
import com.psp.admin.controller.springmvc.req.UpdatePasswordParam;

@Component
public class AdminController {

	public ObjectResult<RAdminBean> getAdmin(GetAdminParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public BaseResult updateName(UpdateNameParam param, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public BaseResult updatePassWord(UpdatePasswordParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectResult<RAdminBean> login(LoginParam param, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

}
