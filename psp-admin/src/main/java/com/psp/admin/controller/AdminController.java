package com.psp.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.psp.admin.controller.res.BaseResult;
import com.psp.admin.controller.res.ListResult;
import com.psp.admin.controller.res.ObjectResult;
import com.psp.admin.controller.res.bean.RAdminBean;
import com.psp.admin.controller.res.bean.RProviderBean;
import com.psp.admin.controller.springmvc.req.DelAdminParam;
import com.psp.admin.controller.springmvc.req.EditAdminParam;
import com.psp.admin.controller.springmvc.req.GetAdminParam;
import com.psp.admin.controller.springmvc.req.GetAdminsParam;
import com.psp.admin.controller.springmvc.req.LoginParam;
import com.psp.admin.controller.springmvc.req.ResetAdminPwdParam;
import com.psp.admin.controller.springmvc.req.UpdateNameParam;
import com.psp.admin.controller.springmvc.req.UpdatePasswordParam;
import com.psp.admin.model.AdminBean;
import com.psp.admin.service.AdminService;
import com.psp.admin.service.exception.ServiceException;
import com.psp.admin.service.res.PageResult;
import com.psp.util.NumUtil;

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
		ObjectResult<RAdminBean> result = new ObjectResult<RAdminBean>();
		try {
			
			String token = param.getToken();
			
			RAdminBean data = parse(adminServiceImpl.getAdminByToken(token));
			result.setData(data);
		} catch (ServiceException e) {
			result.setServiceException(e);
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
			result.setFlag(false);
			result.setMsg(e.getMessage());
		}
		return result;
	}
	
	private RAdminBean parse(AdminBean user) {
		RAdminBean admin = new RAdminBean();
		admin.setAid(user.getAid());
		if(user.getCreateTime() != null) {
			admin.setCreateTime(user.getCreateTime().getTime() / 1000);
		}
		if(user.getLastLoginTime() != null) {
			admin.setLastLoginTime(user.getLastLoginTime().getTime() / 1000);
		}
		admin.setPhoneNum(user.getPhoneNum());
		admin.setStatus(user.getStatus());
		admin.setType(user.getType());
		admin.setUsername(user.getUsername());
		return admin;
	}
	/**
	 * 更新管理员名称
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResult updateName(UpdateNameParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {
			String adminId = (String)request.getAttribute("adminId");
			String name = param.getName();
			boolean flag = adminServiceImpl.updateName(adminId, name);
			result.setFlag(flag);
		} catch (ServiceException e) {
			result.setServiceException(e);
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
			result.setFlag(false);
			result.setMsg(e.getMessage());
		}
		return result;
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
		BaseResult result = new BaseResult();
		try {
			String adminId = (String)request.getAttribute("adminId");
			boolean flag = false;//adminServiceImpl.update(adminId);
			result.setFlag(flag);
		} catch (ServiceException e) {
			result.setServiceException(e);
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
			result.setFlag(false);
			result.setMsg(e.getMessage());
		}
		return result;
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
		ListResult<RAdminBean> result = new ListResult<RAdminBean>();
		try {
			String adminId = (String)request.getAttribute("adminId");
			int page = NumUtil.toInt(param.getPage(), 0);
			int pageSize = NumUtil.toInt(param.getPagesize(), 20);
			String key = param.getKey();
			PageResult<RAdminBean> resList = adminServiceImpl.getList(adminId, page, pageSize, key);
			if(resList == null) {
				result.setData(null);
				result.setTotalSize(0);
				return result;
			}
			int totalSize = resList.getCount();
			List<RAdminBean> lists = resList.getData();
			result.setData(lists);
			result.setTotalSize(totalSize);
		} catch (ServiceException e) {
			result.setServiceException(e);
		}
		return result;
	}
	
	/**
	 * 编辑新建管理员
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResult eidt(EditAdminParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {
			String adminId = (String)request.getAttribute("adminId");
			String aid = param.getAid();
			String pid = param.getPid();
			String name = param.getUsername();
			String password = param.getPassword();
			String confirmPwd = param.getConfirmPwd();
			String phone = param.getPhoneNum();
			int type = NumUtil.toInt(param.getType(), 0);
			boolean flag = adminServiceImpl.editAdmin(adminId, aid, pid, name, password, confirmPwd, phone, type);
			result.setFlag(flag);
		} catch (ServiceException e) {
			result.setServiceException(e);
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
			result.setFlag(false);
			result.setMsg(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 删除账号
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResult del(DelAdminParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {
			String adminId = (String)request.getAttribute("adminId");
			String aid = param.getAid();
			boolean flag = adminServiceImpl.delAdmin(adminId, aid);
			result.setFlag(flag);
		} catch (ServiceException e) {
			result.setServiceException(e);
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
			result.setFlag(false);
			result.setMsg(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 重置密码
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResult resetPwd(ResetAdminPwdParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {
			String adminId = (String)request.getAttribute("adminId");
			String aid = param.getAid();
			boolean flag = adminServiceImpl.resetPwd(adminId, aid);
			result.setFlag(flag);
		} catch (ServiceException e) {
			result.setServiceException(e);
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
			result.setFlag(false);
			result.setMsg(e.getMessage());
		}
		return result;
	}

}
