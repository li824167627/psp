package com.psp.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.psp.admin.controller.res.BaseResult;
import com.psp.admin.controller.res.ListResult;
import com.psp.admin.controller.res.ObjectResult;
import com.psp.admin.controller.res.bean.RAdminBean;
import com.psp.admin.controller.springmvc.req.DelAdminParam;
import com.psp.admin.controller.springmvc.req.EditAdminParam;
import com.psp.admin.controller.springmvc.req.GetAdminParam;
import com.psp.admin.controller.springmvc.req.GetAdminsParam;
import com.psp.admin.controller.springmvc.req.LoginParam;
import com.psp.admin.controller.springmvc.req.UpdateNameParam;
import com.psp.admin.controller.springmvc.req.UpdatePasswordParam;
import com.psp.admin.service.AdminService;

@Component
public class AdminController {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	AdminService adminServiceImpl;
	/**
	 * 获取管理员详情
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public ObjectResult<RAdminBean> getAdmin(GetAdminParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 更新管理员名称
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResult updateName(UpdateNameParam param, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 更新密码
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResult updatePassWord(UpdatePasswordParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 登录
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public ObjectResult<RAdminBean> login(LoginParam param, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 获取管理员账号列表
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public ListResult<RAdminBean> getList(GetAdminsParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 编辑新建管理员
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResult eidt(EditAdminParam param, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 删除账号
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResult del(DelAdminParam param, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

}
