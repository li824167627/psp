package com.psp.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.psp.admin.controller.res.ObjectResult;
import com.psp.admin.controller.res.bean.RAreaListBean;
import com.psp.admin.service.AreaService;
import com.psp.admin.service.exception.ServiceException;
@Component
public class AreaController {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	AreaService areaServiceImpl;
	
	
	public ObjectResult<RAreaListBean> getAllArea(HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RAreaListBean> result = new ObjectResult<>();
		try {
			RAreaListBean bean = areaServiceImpl.getAreas();
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
