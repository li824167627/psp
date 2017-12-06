package com.psp.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.psp.util.NumUtil;
import com.psp.web.controller.res.BaseResult;
import com.psp.web.controller.res.ListResult;
import com.psp.web.controller.res.ObjectResult;
import com.psp.web.controller.res.bean.RCategoryBean;
import com.psp.web.controller.res.bean.RCategoryJSONBean;
import com.psp.web.controller.springmvc.req.GetServiceParam;
import com.psp.web.controller.springmvc.req.SubmitOrderParam;
import com.psp.web.service.OrderService;
import com.psp.web.service.exception.ServiceException;
import com.psp.web.service.res.PageResult;

@Component
public class OrderController {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	OrderService orderServiceImpl;

	public ObjectResult<RCategoryJSONBean> getAllService(HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RCategoryJSONBean> result = new ObjectResult<>();
		try {
			RCategoryJSONBean bean = orderServiceImpl.getAllServices();
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

	public ObjectResult<RCategoryJSONBean> getCategories(HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RCategoryJSONBean> result = new ObjectResult<>();
		try {
			RCategoryJSONBean bean = orderServiceImpl.getCategories();
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

	public ListResult<RCategoryBean> getService(GetServiceParam param, HttpServletRequest request,
			HttpServletResponse response) {
		ListResult<RCategoryBean> result = new ListResult<>();
		try {
			int parentId = NumUtil.toInt(param.getParentId(), 0);
			
			PageResult<RCategoryBean> resList = orderServiceImpl.getService(parentId);
			if(resList == null) {
				result.setData(null);
				result.setTotalSize(0);
				return result;
			}
			int totalSize = resList.getCount();
			List<RCategoryBean> lists = resList.getData();
			result.setData(lists);
			result.setTotalSize(totalSize);
		} catch (ServiceException e) {
			result.setServiceException(e);
		}
		return result;
	}

	public BaseResult submitOrder(SubmitOrderParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {
			String name = param.getUserName();
			String phoneNum = param.getPhoneNum();
			String content = param.getContent();
			int cid = NumUtil.toInt(param.getCid(), 0);
			boolean flag = orderServiceImpl.submitOrder(cid, name, phoneNum, content);
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
