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
import com.psp.admin.controller.res.bean.RAccountBean;
import com.psp.admin.controller.res.bean.RCategoryJSONBean;
import com.psp.admin.controller.res.bean.RProviderBean;
import com.psp.admin.controller.springmvc.req.AddProviderAccountParam;
import com.psp.admin.controller.springmvc.req.AddProviderParam;
import com.psp.admin.controller.springmvc.req.AddProviderServiceParam;
import com.psp.admin.controller.springmvc.req.DelProviderAccountParam;
import com.psp.admin.controller.springmvc.req.DelProviderServiceParam;
import com.psp.admin.controller.springmvc.req.EditProviderParam;
import com.psp.admin.controller.springmvc.req.GetProviderAccountListParam;
import com.psp.admin.controller.springmvc.req.GetProviderDetailParam;
import com.psp.admin.controller.springmvc.req.GetProviderListParam;
import com.psp.admin.controller.springmvc.req.GetProviderServiceListParam;
import com.psp.admin.controller.springmvc.req.ResetProviderPwdParam;
import com.psp.admin.service.CategoryService;
import com.psp.admin.service.ProviderService;
import com.psp.admin.service.exception.ServiceException;
import com.psp.admin.service.res.PageResult;
import com.psp.util.NumUtil;

@Component
public class ProviderController {
	
	Logger logger = Logger.getLogger(this.getClass());
	

	@Autowired
	ProviderService providerServiceImpl;
	
	@Autowired
	CategoryService categoryServiceImpl;
	
	/**
	 * 新建服务商，返回账户
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public ObjectResult<RAccountBean> addProvider(AddProviderParam param, HttpServletRequest request,
			HttpServletResponse response) {
		ObjectResult<RAccountBean> result = new ObjectResult<RAccountBean>();
		try {
			String adminId = (String)request.getAttribute("adminId");
			String name = param.getName();
			String address = param.getAddress();
			String contact = param.getContact();
			String phoneNum = param.getPhoneNum();
			String content = param.getContent();
			String password = param.getPassword();
			String confirmPwd = param.getConfirmPwd();
			String cids = param.getCid();
			RAccountBean bean = providerServiceImpl.addProvider(name, address, contact, phoneNum, content, password, confirmPwd, cids, adminId);
			if (bean != null) {
				result.setData(bean);
			}
		} catch (ServiceException e) {
			logger.info(e);
			e.printStackTrace();
			result.setFlag(false);
			result.setMsg(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 获取服务商列表
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public ListResult<RProviderBean> getList(GetProviderListParam param, HttpServletRequest request,
			HttpServletResponse response) {
		ListResult<RProviderBean> result = new ListResult<>();
		try {
			String adminId = (String)request.getAttribute("adminId");
			int page = NumUtil.toInt(param.getPage(), 0);
			int pageSize = NumUtil.toInt(param.getPagesize(), 20);
			int cid = NumUtil.toInt(param.getCid(), 0);
			PageResult<RProviderBean> resList = providerServiceImpl.getList(adminId, page, pageSize, cid);
			if(resList == null) {
				result.setData(null);
				result.setTotalSize(0);
				return result;
			}
			int totalSize = resList.getCount();
			List<RProviderBean> lists = resList.getData();
			result.setData(lists);
			result.setTotalSize(totalSize);
		} catch (ServiceException e) {
			result.setServiceException(e);
		}
		return result;
	}
	
	/**
	 * 获取服务商详情
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public ObjectResult<RProviderBean> getDetail(GetProviderDetailParam param, HttpServletRequest request,
			HttpServletResponse response) {
		ObjectResult<RProviderBean> result = new ObjectResult<RProviderBean>();
		try {

			String adminId = (String)request.getAttribute("adminId");
			String pid = param.getPid();
			
			RProviderBean data = providerServiceImpl.getDetail(adminId, pid);
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
	 * 获取服务商账号列表
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public ListResult<RAccountBean> getAccountList(GetProviderAccountListParam param, HttpServletRequest request,
			HttpServletResponse response) {
		ListResult<RAccountBean> result = new ListResult<>();
		try {
			String adminId = (String)request.getAttribute("adminId");
			int page = NumUtil.toInt(param.getPage(), 0);
			int pageSize = NumUtil.toInt(param.getPagesize(), 20);
			String pid =param.getPid();
			PageResult<RAccountBean> resList = providerServiceImpl.getAccountList(adminId, page, pageSize, pid);
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
	 * 新建服务商账号
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResult addAccount(AddProviderAccountParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {
			String adminId = (String)request.getAttribute("adminId");
			String pid = param.getPid();
			String name = param.getName();
			String phone = param.getPhone();
			String password = param.getPassword();
			boolean flag = providerServiceImpl.addAccount(adminId, name, phone, password, pid);
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
	 * 重置账号密码
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResult resetPwd(ResetProviderPwdParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {
			String adminId = (String)request.getAttribute("adminId");
			String aid = param.getAid();
			boolean flag = providerServiceImpl.resetPwd(adminId, aid);
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
			String adminId = (String)request.getAttribute("adminId");
			String aid = param.getAid();
			boolean flag = providerServiceImpl.delAccount(adminId, aid);
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

	public BaseResult eidtProvider(EditProviderParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {
			String name = param.getName();
			String pid = param.getPid();
			String address = param.getAddress();
			String contact = param.getContact();
			String phoneNum = param.getPhoneNum();
			String content = param.getContent();
			boolean flag = providerServiceImpl.editProvider(pid, name, address, contact, phoneNum, content);
			result.setFlag(flag);
		} catch (ServiceException e) {
			logger.info(e);
			e.printStackTrace();
			result.setFlag(false);
			result.setMsg(e.getMessage());
		}
		return result;
	}

	public BaseResult addService(AddProviderServiceParam param, HttpServletRequest request,
			HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {
			String pid = param.getPid();
			String cid = param.getCid();
			boolean flag = providerServiceImpl.addService(pid, cid);
			result.setFlag(flag);
		} catch (ServiceException e) {
			logger.info(e);
			e.printStackTrace();
			result.setFlag(false);
			result.setMsg(e.getMessage());
		}
		return result;
	}

	public BaseResult delService(DelProviderServiceParam param, HttpServletRequest request,
			HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {
			String pid = param.getPid();
			String cid = param.getCid();
			boolean flag = providerServiceImpl.delService(pid, cid);
			result.setFlag(flag);
		} catch (ServiceException e) {
			logger.info(e);
			e.printStackTrace();
			result.setFlag(false);
			result.setMsg(e.getMessage());
		}
		return result;
	}

	
	
	public ObjectResult<RCategoryJSONBean> getServiceList(GetProviderServiceListParam param, HttpServletRequest request,
			HttpServletResponse response) {
		ObjectResult<RCategoryJSONBean> result = new ObjectResult<>();
		try {
			String pid = param.getPid();
			RCategoryJSONBean bean = categoryServiceImpl.getServiceList(pid);
			if (bean != null) {
				result.setData(bean);
			}
		} catch (ServiceException e) {
			logger.info(e);
			e.printStackTrace();
			result.setFlag(false);
			result.setMsg(e.getMessage());
		}
		return result;
	}

}
