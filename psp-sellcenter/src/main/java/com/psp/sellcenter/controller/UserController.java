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
import com.psp.sellcenter.controller.springmvc.req.EditUserLabelParam;
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
			String sid = (String)request.getAttribute("sellerId");
			int page = NumUtil.toInt(param.getPage(), 0);
			int pageSize = NumUtil.toInt(param.getPagesize(), 20);
			int filteType = NumUtil.toInt(param.getFilteType(), 0);//筛选类型
			int stype = NumUtil.toInt(param.getStype(), 0);//搜索类型
			int status = NumUtil.toInt(param.getStatus(), -1);// 沟通状态
			String key = param.getKey();//关键字
			
			PageResult<RUserBean> resList = userServiceImpl.getUsers2Seller(sid, page, pageSize, filteType, stype, key, status);
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
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
			result.setFlag(false);
			result.setMsg(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 新建客户
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
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
			String visitDest = param.getVisitDest();
			int visitNum = NumUtil.toInt(param.getVisitNum(), 1);
			String refCompany = param.getRefCompany();
			String referrer = param.getReferrer();
			String visitTime = param.getVisitTime();
			String escort = param.getEscort();
			String introducer = param.getIntroducer();
			String visitFlow = param.getVisitflow();
			String remark = param.getRemark();
			int cType = NumUtil.toInt(param.getCtype(), 1);
			
			RUserBean data = userServiceImpl.addUser(sid, name, phoneNum, companyName, position, label, isUpdate, isClaim,
				visitDest,visitNum,refCompany,referrer,visitTime,escort,introducer,visitFlow,remark,cType);
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
	 * 编辑客户
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public ObjectResult<RUserBean> edit(EditUserParam param, HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RUserBean> result = new ObjectResult<>();
		try {

			String sid = (String)request.getAttribute("sellerId");
			
			String name = param.getName();
			String phoneNum = param.getPhoneNum();
			String companyName = param.getCompanyName();
			String position = param.getPosition();
			String label = param.getLabel();
			String uid = param.getUserId();
			String visitDest = param.getVisitDest();
			int visitNum = NumUtil.toInt(param.getVisitNum(), 1);
			String refCompany = param.getRefCompany();
			String referrer = param.getReferrer();
			String visitTime = param.getVisitTime();
			String escort = param.getEscort();
			String introducer = param.getIntroducer();
			String visitFlow = param.getVisitflow();
			String remark = param.getRemark();
			int cType = NumUtil.toInt(param.getCtype(), 1);
			RUserBean data = userServiceImpl.eidtUser(sid, name, phoneNum, companyName, position, label, uid,
					visitDest,visitNum,refCompany,referrer,visitTime,escort,introducer,visitFlow,remark,cType);
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

			String sid = (String)request.getAttribute("sellerId");
			int isAllot = NumUtil.toInt(param.getStatus(), 0);
			int userNum = userServiceImpl.getUserNum2Seller(sid, isAllot);
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
	
	/**
	 * 编辑客户评级
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResult editLevel(EditUserLevelParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {

			String sid = (String)request.getAttribute("sellerId");
			
			String uid = param.getUserId();
			int level = NumUtil.toInt(param.getLevel(), 0);
			
			boolean flag = userServiceImpl.eidtUserLevel(sid, level, uid);
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
	 * 归档客户
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResult archive(ArchiveParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {

			String sid = (String)request.getAttribute("sellerId");
			
			String uid = param.getUserId();
			boolean flag = userServiceImpl.archive(sid, uid);
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
	 * 获取客户详情
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public ObjectResult<RUserBean> getDetail(GetUserDetailParam param, HttpServletRequest request,
			HttpServletResponse response) {
		ObjectResult<RUserBean> result = new ObjectResult<>();
		try {

			String sid = (String)request.getAttribute("sellerId");
			String uid = param.getUid();
			
			RUserBean data = userServiceImpl.getDetail(sid, uid);
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
	 * 获取客户操作列表
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public ListResult<RUserLogsBean> getUserLogs(GetUserLogsParam param, HttpServletRequest request,
			HttpServletResponse response) {
		ListResult<RUserLogsBean> result = new ListResult<>();
		try {
			String sid = (String)request.getAttribute("sellerId");
			String uid = param.getUid();
			String key = param.getKey();//关键字
			
			PageResult<RUserLogsBean> resList = userServiceImpl.getUserLogs(sid, uid, key);
			
			if(resList == null) {
				result.setData(null);
				result.setTotalSize(0);
				return result;
			}
			int totalSize = resList.getCount();
			List<RUserLogsBean> lists = resList.getData();
			result.setData(lists);
			result.setTotalSize(totalSize);
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
	 * 编辑客户标签
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResult editLabel(EditUserLabelParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {

			String sid = (String)request.getAttribute("sellerId");
			
			String uid = param.getUserId();
			String label = param.getLabel();
			
			boolean flag = userServiceImpl.eidtUserLabel(sid, label, uid);
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
