package com.psp.provider.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.psp.provider.cache.dao.AccountCacheDao;
import com.psp.provider.controller.res.BaseResult;
import com.psp.provider.controller.res.ListResult;
import com.psp.provider.controller.res.ObjectResult;
import com.psp.provider.controller.res.bean.RAccountBean;
import com.psp.provider.controller.springmvc.req.AddProviderAccountParam;
import com.psp.provider.controller.springmvc.req.ConfirmFindPwdCodeParam;
import com.psp.provider.controller.springmvc.req.DelProviderAccountParam;
import com.psp.provider.controller.springmvc.req.GetAccountParam;
import com.psp.provider.controller.springmvc.req.GetProviderAccountListParam;
import com.psp.provider.controller.springmvc.req.LoginParam;
import com.psp.provider.controller.springmvc.req.ResetProviderPwdParam;
import com.psp.provider.controller.springmvc.req.ResetPwdParam;
import com.psp.provider.controller.springmvc.req.SendFindPwdCodeParam;
import com.psp.provider.controller.springmvc.req.SendVCodeParam;
import com.psp.provider.controller.springmvc.req.UpdateAccountNameParam;
import com.psp.provider.model.AccountBean;
import com.psp.provider.model.Code;
import com.psp.provider.service.AccountService;
import com.psp.provider.service.exception.ServiceException;
import com.psp.provider.service.impl.res.PageResult;
import com.psp.util.AppTextUtil;
import com.psp.util.NumUtil;

@Component
public class AccountController {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	AccountService accountServiceImpl;
	
	@Autowired
	AccountCacheDao accountCacheImpl;

	public ObjectResult<RAccountBean> login(LoginParam param, HttpServletRequest request,
			HttpServletResponse response) {
		ObjectResult<RAccountBean> result = new ObjectResult<RAccountBean>();

		String phone = param.getPhoneNum();
		String pwd = param.getPassword();
		String vcode = param.getImgCode();
		String device = param.getDevice();
		try {
			String sessionId = request.getSession().getId();
			String ip = AppTextUtil.getIpAddr(request);
			RAccountBean user = accountServiceImpl.login(sessionId, phone, pwd, vcode, device, ip);
			logger.info("login user is:" + user);
			if (user != null) {
				result.setToken(sessionId);
				result.setData(user);
				result.setFlag(true);
			} else {
				Code code = accountCacheImpl.getLoginCode(phone);
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

	public BaseResult sendVCode(SendVCodeParam param, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectResult<RAccountBean> sendFindPwdCode(SendFindPwdCodeParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectResult<RAccountBean> confirmFindPwdCode(ConfirmFindPwdCodeParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public BaseResult resetPwd(ResetPwdParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {

			AccountBean account = (AccountBean)request.getAttribute("account");
			String pwd = param.getOldPwd();
			String newPwd = param.getPassword();
			String subPwd = param.getConfirmPwd();
			boolean flag = accountServiceImpl.resetPwd(account, pwd, newPwd, subPwd);
			if (flag) {
				result.setFlag(true);
			}
		} catch (ServiceException e) {
			result.setFlag(false);
			result.setMsg(e.getServiceMsg());
		}
		return result;
	}

	public ObjectResult<RAccountBean> getAccount(GetAccountParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectResult<RAccountBean> updateName(UpdateAccountNameParam param, HttpServletRequest request,
			HttpServletResponse response) {
		ObjectResult<RAccountBean> result = new ObjectResult<RAccountBean>();
		try {

			AccountBean account = (AccountBean)request.getAttribute("account");
			String name = param.getName();
			RAccountBean user = accountServiceImpl.updateName(account, name);
			if (user != null) {
				result.setData(user);
				result.setFlag(true);
			} 
		} catch (ServiceException e) {
			result.setFlag(false);
			result.setRescode(e.getServiceCode());
			result.setMsg(e.getServiceMsg());
		}
		return result;
	}
	
	/**
	 * 获取账号列表
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public ListResult<RAccountBean> getAccountList(GetProviderAccountListParam param, HttpServletRequest request,
			HttpServletResponse response) {
		ListResult<RAccountBean> result = new ListResult<>();
		try {
			AccountBean account = (AccountBean)request.getAttribute("account");
			int page = NumUtil.toInt(param.getPage(), 0);
			int pageSize = NumUtil.toInt(param.getPagesize(), 20);
			PageResult<RAccountBean> resList = accountServiceImpl.getAccountList(account, page, pageSize);
			if(resList == null) {
				result.setData(null);
				result.setTotalSize(0);
				return result;
			}
			int totalSize = resList.getCount();
			List<RAccountBean> lists = resList.getData();
			result.setData(lists);
			result.setTotalSize(totalSize);
		} catch (ServiceException e) {
			result.setServiceException(e);
		}
		return result;
	}
	
	/**
	 * 重置账号密码
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResult resetAccountPwd(ResetProviderPwdParam param, HttpServletRequest request,
			HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {
			AccountBean account = (AccountBean)request.getAttribute("account");
			String aid = param.getAid();
			boolean flag = accountServiceImpl.resetAccountPwd(account, aid);
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
	 * 新建账号
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResult addAccount(AddProviderAccountParam param, HttpServletRequest request,
			HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {
			AccountBean account = (AccountBean)request.getAttribute("account");
			String name = param.getName();
			String phone = param.getPhone();
			String password = param.getPassword();
			boolean flag = accountServiceImpl.addAccount(account, name, phone, password);
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
	public BaseResult delAccount(DelProviderAccountParam param, HttpServletRequest request,
			HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {
			AccountBean account = (AccountBean)request.getAttribute("account");
			String aid = param.getAid();
			boolean flag = accountServiceImpl.delAccount(account, aid);
			result.setFlag(flag);
		} catch (ServiceException e) {
			result.setServiceException(e);
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
			result.setFlag(false);
			result.setMsg(e.toString());
			
		}
		return result;
	}

}
