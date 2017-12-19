package com.psp.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.psp.admin.cache.dao.AdminCacheDao;
import com.psp.admin.controller.res.BaseResult;
import com.psp.admin.controller.res.ListResult;
import com.psp.admin.controller.res.ObjectResult;
import com.psp.admin.controller.res.bean.RAdminBean;
import com.psp.admin.controller.res.bean.ROrderStatisticsBean;
import com.psp.admin.controller.res.bean.RUserBean;
import com.psp.admin.controller.res.bean.RUserStatisticsBean;
import com.psp.admin.controller.springmvc.req.ConfirmFindPwdCodeParam;
import com.psp.admin.controller.springmvc.req.DelAdminParam;
import com.psp.admin.controller.springmvc.req.EditAdminParam;
import com.psp.admin.controller.springmvc.req.GetAdminParam;
import com.psp.admin.controller.springmvc.req.GetAdminsParam;
import com.psp.admin.controller.springmvc.req.LoginParam;
import com.psp.admin.controller.springmvc.req.ResetAdminPwdParam;
import com.psp.admin.controller.springmvc.req.SendFindPwdCodeParam;
import com.psp.admin.controller.springmvc.req.SendVCodeParam;
import com.psp.admin.controller.springmvc.req.UpdateNameParam;
import com.psp.admin.controller.springmvc.req.UpdatePasswordParam;
import com.psp.admin.model.AdminBean;
import com.psp.admin.model.Code;
import com.psp.admin.service.AdminService;
import com.psp.admin.service.exception.ServiceException;
import com.psp.admin.service.res.PageResult;
import com.psp.util.AppTextUtil;
import com.psp.util.NumUtil;

@Component
public class AdminController {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	AdminService adminServiceImpl;
	
	@Autowired
	AdminCacheDao adminCacheImpl;
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
	public ObjectResult<RAdminBean> updateName(UpdateNameParam param, HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RAdminBean> result = new ObjectResult<RAdminBean>();
		try {
			String adminId = (String)request.getAttribute("adminId");
			String name = param.getName();
			RAdminBean data = adminServiceImpl.updateName(adminId, name);
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
			String pwd = param.getOldPwd();
			String newPwd = param.getPassword();
			String subPwd = param.getConfirmPwd();
			boolean flag = adminServiceImpl.updatePassWord(adminId, pwd, newPwd, subPwd);
			if (flag) {
				result.setFlag(true);
			}
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
		ObjectResult<RAdminBean> result = new ObjectResult<RAdminBean>();

		String phone = param.getPhoneNum();
		String pwd = param.getPassword();
		String vcode = param.getImgCode();
		String device = param.getDevice();
		try {
			String sessionId = request.getSession().getId();
			String ip = AppTextUtil.getIpAddr(request);
			RAdminBean user = adminServiceImpl.login(sessionId, phone, pwd, vcode, device, ip);
			logger.info("login user is:" + user);
			if (user != null) {
				result.setToken(sessionId);
				result.setData(user);
				result.setFlag(true);
			} else {
				Code code = adminCacheImpl.getLoginCode(phone);
				if (code != null) {
					result.setErrorCount(code.getNum());
				}
			}
		} catch (ServiceException e) {
			result.setFlag(false);
			result.setRescode(e.getServiceCode());
			result.setMsg(e.getServiceMsg());
		}
		return result;
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
	public BaseResult resetAdminPwd(ResetAdminPwdParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {
			String adminId = (String)request.getAttribute("adminId");
			String aid = param.getAid();
			boolean flag = adminServiceImpl.resetAdminPwd(adminId, aid);
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

	public BaseResult sendVCode(SendVCodeParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();

		String phone = param.getPhone();
		int type = NumUtil.toInt(param.getType(), 0);
		String imgcode = param.getImgCode();
		String imgkey = param.getImgKey();
		try {
			if (type != 3) {// 重置密码不需要图形验证码
				adminServiceImpl.checkImgCode(imgkey, imgcode);
			}
			boolean flag = adminServiceImpl.sendVCode(type, phone);
			result.setFlag(flag);
		} catch (ServiceException e) {
			result.setServiceException(e);
		}
		return result;
	}

	public ObjectResult<RUserBean> sendFindPwdCode(SendFindPwdCodeParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectResult<RUserBean> confirmFindPwdCode(ConfirmFindPwdCodeParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ObjectResult<ROrderStatisticsBean> getOrderStatistics(HttpServletRequest request,
			HttpServletResponse response) {
		ObjectResult<ROrderStatisticsBean> result = new ObjectResult<ROrderStatisticsBean>();

		try {
			String adminId = (String)request.getAttribute("adminId");
			ROrderStatisticsBean user = adminServiceImpl.getOrderStatistics(adminId);
			result.setData(user);
			result.setFlag(true);
		} catch (ServiceException e) {
			result.setFlag(false);
			result.setRescode(e.getServiceCode());
			result.setMsg(e.getServiceMsg());
		}
		return result;
	}

	public ObjectResult<RUserStatisticsBean> getUserStatistics(HttpServletRequest request,
			HttpServletResponse response) {
		ObjectResult<RUserStatisticsBean> result = new ObjectResult<RUserStatisticsBean>();

		try {
			String adminId = (String)request.getAttribute("adminId");
			RUserStatisticsBean user = adminServiceImpl.getUserStatistics(adminId);
			result.setData(user);
			result.setFlag(true);
		} catch (ServiceException e) {
			result.setFlag(false);
			result.setRescode(e.getServiceCode());
			result.setMsg(e.getServiceMsg());
		}
		return result;
	}
	


}
