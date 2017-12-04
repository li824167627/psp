package com.psp.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.psp.admin.controller.res.ListResult;
import com.psp.admin.controller.res.bean.RAreaListBean;
import com.psp.admin.service.AreaService;
import com.psp.admin.service.res.PageResult;
@Component
public class AreaController {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	AreaService areaServiceImpl;
	
	
	public ListResult<RAreaListBean> getAllArea(HttpServletRequest request, HttpServletResponse response) {
		ListResult<RAreaListBean> result = new ListResult<>();
		PageResult<RAreaListBean> resData = areaServiceImpl.getAreas();
		if (resData != null) {
			result.setTotalSize(resData.getCount());
			result.setData(resData.getData());
		}
		result.setFlag(true);
		return result;
	}


}
