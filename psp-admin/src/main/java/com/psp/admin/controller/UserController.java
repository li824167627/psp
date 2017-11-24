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
import com.psp.admin.controller.res.bean.RUserBean;
import com.psp.admin.controller.res.bean.RUserLogsBean;
import com.psp.admin.controller.springmvc.req.AllotParam;
import com.psp.admin.controller.springmvc.req.ArchiveParam;
import com.psp.admin.controller.springmvc.req.GetUserDetailParam;
import com.psp.admin.controller.springmvc.req.GetUserLogsParam;
import com.psp.admin.controller.springmvc.req.GetUserNumParam;
import com.psp.admin.controller.springmvc.req.GetUsersParam;
import com.psp.admin.service.UserService;
import com.psp.admin.service.exception.ServiceException;
import com.psp.admin.service.res.PageResult;
import com.psp.util.NumUtil;

@Component
public class UserController {


	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	UserService userServiceImpl;
	
	/**
	 * 获取客户列表
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public ListResult<RUserBean> getUsers(GetUsersParam param, HttpServletRequest request,
			HttpServletResponse response) {
		ListResult<RUserBean> result = new ListResult<>();
		try {
			int page = NumUtil.toInt(param.getPage(), 0);
			int pageSize = NumUtil.toInt(param.getPagesize(), 20);
			int filteType = NumUtil.toInt(param.getFilteType(), 0);//筛选类型
			int stype = NumUtil.toInt(param.getStype(), 0);//搜索类型
			int status = NumUtil.toInt(param.getStatus(), 0);// 沟通状态
			String key = param.getKey();//关键字
			
			PageResult<RUserBean> resList = userServiceImpl.getUsers(page, pageSize, filteType, stype, key, status);
			if(resList == null) {
				result.setData(null);
				result.setTotalSize(0);
				return result;
			}
			int totalSize = resList.getCount();
			List<RUserBean> lists = resList.getData();
			result.setData(lists);
			result.setTotalSize(totalSize);
		} catch (ServiceException e) {
			result.setServiceException(e);
		}
		return result;
	}
	
	/**
	 * 根据状态获取客户数量
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public ObjectResult<Integer> getUserNum(GetUserNumParam param, HttpServletRequest request,
			HttpServletResponse response) {
		ObjectResult<Integer> result = new ObjectResult<>();
		try {
			int isAllot = NumUtil.toInt(param.getStatus(), 0);
			int userNum = userServiceImpl.getUserNum(isAllot);
			result.setData(userNum);
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
	
	public BaseResult archive(ArchiveParam param, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public BaseResult allot(AllotParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {

			String aid = (String)request.getAttribute("adminId");
			
			String uid = param.getUid();
			String sid = param.getSid();
			boolean flag = userServiceImpl.allot(aid, sid, uid);
			result.setFlag(flag);
		} catch (ServiceException e) {
			result.setServiceException(e);
		} catch (Exception e) {
			logger.info(e);
			result.setFlag(false);
			result.setMsg(e.getMessage());
		}
		return result;
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
