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
import com.psp.admin.controller.res.bean.RCategoryBean;
import com.psp.admin.controller.res.bean.RCategoryJSONBean;
import com.psp.admin.controller.springmvc.req.AddServiceParam;
import com.psp.admin.controller.springmvc.req.EditServiceParam;
import com.psp.admin.controller.springmvc.req.GetServiceParam;
import com.psp.admin.service.CategoryService;
import com.psp.admin.service.exception.ServiceException;
import com.psp.admin.service.res.PageResult;
import com.psp.util.NumUtil;

@Component
public class ServiceController {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	CategoryService categoryServiceImpl;
	
	/**
	 * 获取百度图表展示的数据结构
	 * @param request
	 * @param response
	 * @return
	 */
	public ObjectResult<RCategoryJSONBean> getAllService(HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RCategoryJSONBean> result = new ObjectResult<>();
		try {
			RCategoryJSONBean bean = categoryServiceImpl.getAllServices();
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
	 * 获取服务的一级二级分类
	 * @param request
	 * @param response
	 * @return
	 */
	public ObjectResult<RCategoryJSONBean> getCategories(HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RCategoryJSONBean> result = new ObjectResult<>();
		try {
			RCategoryJSONBean bean = categoryServiceImpl.getCategories();
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
	 * 根据二级分类获取服务
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public ListResult<RCategoryBean> getService(GetServiceParam param, HttpServletRequest request,
			HttpServletResponse response) {
		ListResult<RCategoryBean> result = new ListResult<>();
		try {
			int parentId = NumUtil.toInt(param.getParentId(), 0);
			
			PageResult<RCategoryBean> resList = categoryServiceImpl.getService(parentId);
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
	
	/**
	 * 添加服务
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResult addService(AddServiceParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {
			String aid = (String)request.getAttribute("adminId");
			int parentId = NumUtil.toInt(param.getParentId(), 0);
			String name = param.getName();
			int sort = NumUtil.toInt(param.getSort(), 1);
			int isService = NumUtil.toInt(param.getIsService(), 0);
			boolean flag = categoryServiceImpl.addService(aid, parentId, name, sort, isService);
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
	
	/**
	 * 编辑服务
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResult editService(EditServiceParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {
			String aid = (String)request.getAttribute("adminId");
			int cid = NumUtil.toInt(param.getCid(), 0);
			int parentId = NumUtil.toInt(param.getParentId(), 0);
			String name = param.getName();
			int sort = NumUtil.toInt(param.getSort(), 1);
			int isService = NumUtil.toInt(param.getIsService(), 0);
			boolean flag = categoryServiceImpl.editService(aid, cid, parentId, name, sort, isService);
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

}
