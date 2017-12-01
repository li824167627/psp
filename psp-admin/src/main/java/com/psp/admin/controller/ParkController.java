package com.psp.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.psp.admin.controller.res.BaseResult;
import com.psp.admin.controller.res.ListResult;
import com.psp.admin.controller.res.bean.RParkBean;
import com.psp.admin.controller.springmvc.req.DelParkParam;
import com.psp.admin.controller.springmvc.req.EditParkParam;
import com.psp.admin.controller.springmvc.req.GetParksParam;
import com.psp.admin.service.ParkService;
import com.psp.admin.service.exception.ServiceException;
import com.psp.admin.service.res.PageResult;
import com.psp.util.NumUtil;
@Component
public class ParkController {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	ParkService parkServiceImpl;
	
	/**
	 * 获取园区列表
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public ListResult<RParkBean> getList(GetParksParam param, HttpServletRequest request,
			HttpServletResponse response) {
		ListResult<RParkBean> result = new ListResult<>();
		try {
			String adminId = (String)request.getAttribute("adminId");
			int page = NumUtil.toInt(param.getPage(), 0);
			int pageSize = NumUtil.toInt(param.getPagesize(), 20);
			String key = param.getKey();//关键字
			
			PageResult<RParkBean> resList = parkServiceImpl.getList(adminId, page, pageSize, key);
			if(resList == null) {
				result.setData(null);
				result.setTotalSize(0);
				return result;
			}
			int totalSize = resList.getCount();
			List<RParkBean> lists = resList.getData();
			result.setData(lists);
			result.setTotalSize(totalSize);
		} catch (ServiceException e) {
			result.setServiceException(e);
		}
		return result;
	}
	
	/**
	 * 编辑新建园区
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResult eidt(EditParkParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {
			String adminId = (String)request.getAttribute("adminId");
			String pid = param.getPid();
			String name = param.getName();
			String contact = param.getContact();
			String phoneNum = param.getPhoneNum();
			String cityCode = param.getCityCode();
			String province = param.getProvince();
			String city = param.getCity();
			String district = param.getDistrict();
			String coordinate = param.getCoordinate();
			String brief = param.getBrief();
			boolean flag = parkServiceImpl.eidtPark(adminId, name, pid, contact,
					phoneNum, cityCode, province, city, district, coordinate, brief);
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
	 * 删除园区
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResult del(DelParkParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {
			String adminId = (String)request.getAttribute("adminId");
			String pid = param.getPid();
			boolean flag = parkServiceImpl.del(adminId, pid);
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
