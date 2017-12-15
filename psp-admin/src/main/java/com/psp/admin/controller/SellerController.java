package com.psp.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.psp.admin.controller.res.BaseResult;
import com.psp.admin.controller.res.ListResult;
import com.psp.admin.controller.res.bean.RSellerBean;
import com.psp.admin.controller.springmvc.req.DelSellerParam;
import com.psp.admin.controller.springmvc.req.EditSellerParam;
import com.psp.admin.controller.springmvc.req.GetSellersParam;
import com.psp.admin.controller.springmvc.req.ResetSellerPwdParam;
import com.psp.admin.service.SellerService;
import com.psp.admin.service.exception.ServiceException;
import com.psp.admin.service.res.PageResult;
import com.psp.util.NumUtil;

@Component
public class SellerController {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	SellerService sellerServiceImpl;

	public ListResult<RSellerBean> getList(GetSellersParam param, HttpServletRequest request,
			HttpServletResponse response) {
		ListResult<RSellerBean> result = new ListResult<>();
		try {
			String adminId = (String)request.getAttribute("adminId");
			String pid = param.getPid();
			String key = param.getKey();
			int page = NumUtil.toInt(param.getPage(), 0);
			int pageSize = NumUtil.toInt(param.getPagesize(), 20);
			
			PageResult<RSellerBean> resList = sellerServiceImpl.getSellers(page, pageSize, pid, key, adminId);
			if(resList == null) {
				result.setData(null);
				result.setTotalSize(0);
				return result;
			}
			int totalSize = resList.getCount();
			List<RSellerBean> lists = resList.getData();
			result.setData(lists);
			result.setTotalSize(totalSize);
		} catch (ServiceException e) {
			result.setServiceException(e);
		}
		return result;
	}

	public BaseResult eidt(EditSellerParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {
			String adminId = (String)request.getAttribute("adminId");
			String sid = param.getSid();
			String name = param.getName();
			String pid = param.getPid();
			int type = NumUtil.toInt(param.getType(), 0);
			String password = param.getPassword();
			String phoneNum = param.getPhoneNum();
			boolean flag = sellerServiceImpl.EditSeller(adminId, sid, name, password, phoneNum, pid, type);
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

	public BaseResult resetPwd(ResetSellerPwdParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {
			String adminId = (String)request.getAttribute("adminId");
			String sid = param.getSid();
			boolean flag = sellerServiceImpl.resetPwd(adminId, sid);
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

	public BaseResult del(DelSellerParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {
			String adminId = (String)request.getAttribute("adminId");
			String sid = param.getSid();
			boolean flag = sellerServiceImpl.delSeller(adminId, sid);
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
