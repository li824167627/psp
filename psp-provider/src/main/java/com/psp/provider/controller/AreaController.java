package com.psp.provider.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.psp.provider.controller.res.ListResult;
import com.psp.provider.controller.res.bean.RAreaListBean;
import com.psp.provider.service.AreaService;
import com.psp.provider.service.impl.res.PageResult;
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
