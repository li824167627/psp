package com.psp.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.psp.admin.controller.res.BaseResult;
import com.psp.admin.controller.res.ListResult;
import com.psp.admin.controller.res.ObjectResult;
import com.psp.admin.controller.res.bean.RUserBean;
import com.psp.admin.controller.res.bean.RUserLogsBean;
import com.psp.admin.controller.springmvc.req.AllotParam;
import com.psp.admin.controller.springmvc.req.ArchiveParam;
import com.psp.admin.controller.springmvc.req.GetUserDetailParam;
import com.psp.admin.controller.springmvc.req.GetUserLogsParam;
import com.psp.admin.controller.springmvc.req.GetUserNumParam;
import com.psp.admin.controller.springmvc.req.GetUsersParam;
import com.psp.admin.controller.springmvc.req.LoginParam;

@Component
public class UserController {

	public ObjectResult<RUserBean> login(LoginParam param, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public ListResult<RUserBean> getUsers(GetUsersParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectResult<Integer> getUserNum(GetUserNumParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public BaseResult archive(ArchiveParam param, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public BaseResult allot(AllotParam param, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectResult<RUserBean> getDetail(GetUserDetailParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public ListResult<RUserLogsBean> getUserLogs(GetUserLogsParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

}
