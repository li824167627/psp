package com.psp.sellcenter.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.psp.sellcenter.controller.res.BaseResult;
import com.psp.sellcenter.controller.res.ListResult;
import com.psp.sellcenter.controller.res.ObjectResult;
import com.psp.sellcenter.controller.res.bean.RUserBean;
import com.psp.sellcenter.controller.res.bean.RUserLogsBean;
import com.psp.sellcenter.controller.springmvc.req.AddUserParam;
import com.psp.sellcenter.controller.springmvc.req.ArchiveParam;
import com.psp.sellcenter.controller.springmvc.req.EditUserLevelParam;
import com.psp.sellcenter.controller.springmvc.req.EditUserParam;
import com.psp.sellcenter.controller.springmvc.req.GetUserDetailParam;
import com.psp.sellcenter.controller.springmvc.req.GetUserLogsParam;
import com.psp.sellcenter.controller.springmvc.req.GetUserNumParam;
import com.psp.sellcenter.controller.springmvc.req.GetUsersParam;
import com.psp.sellcenter.service.UserService;
import com.psp.sellcenter.service.exception.ServiceException;
import com.psp.sellcenter.service.res.PageResult;
import com.psp.util.NumUtil;

@Component
public class UserController {
	
	Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	UserService userServiceImpl;

	public ListResult<RUserBean> getUsers(GetUsersParam param, HttpServletRequest request,
			HttpServletResponse response) {
		ListResult<RUserBean> result = new ListResult<>();
		try {
			String sid = (String)request.getAttribute("sellerId");
			int page = NumUtil.toInt(param.getPage(), 0);
			int pageSize = NumUtil.toInt(param.getPagesize(), 20);
			int filteType = NumUtil.toInt(param.getFilteType(), 0);//筛选类型
			int stype = NumUtil.toInt(param.getStype(), 0);//搜索类型
			String key = param.getKey();//关键字
			
			PageResult<RUserBean> resList = userServiceImpl.getUsers2Seller(sid, page, pageSize, filteType, stype, key);
			int totalSize = resList.getCount();
			List<RUserBean> lists = resList.getData();
			if(resList == null || totalSize == 0) {
				result.setData(lists);
				result.setTotalSize(totalSize);
				return result;
			}
	
			result.setData(lists);
			result.setTotalSize(totalSize);
		} catch (ServiceException e) {
			result.setServiceException(e);
		}
		return result;
	}

	public ObjectResult<RUserBean> add(AddUserParam param, HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RUserBean> result = new ObjectResult<>();
		try {

			String sid = (String)request.getAttribute("sellerId");
			
			String name = param.getName();
			String phoneNum = param.getPhoneNum();
			String companyName = param.getCompanyName();
			String position = param.getPosition();
			String label = param.getLabel();
			int isUpdate = NumUtil.toInt(param.getIsUpdate(), 0);
			int isClaim = NumUtil.toInt(param.getIsClaim(), 0);
			
			RUserBean data = userServiceImpl.addUser(sid, name, phoneNum, companyName, position, label, isUpdate, isClaim);
			result.setData(data);
		} catch (ServiceException e) {
			result.setServiceException(e);
		} catch (Exception e) {
			result.setFlag(false);
			result.setMsg(e.getMessage());
		}
		return result;
	}

	public ObjectResult<RUserBean> edit(EditUserParam param, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectResult<Integer> getUserNum(GetUserNumParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public BaseResult editLevel(EditUserLevelParam param, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public BaseResult archive(ArchiveParam param, HttpServletRequest request, HttpServletResponse response) {
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
