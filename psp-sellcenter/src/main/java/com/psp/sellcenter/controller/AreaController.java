package com.psp.sellcenter.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.psp.sellcenter.controller.res.ListResult;
import com.psp.sellcenter.controller.res.bean.RAreaListBean;

@Component
public class AreaController {

	Logger logger = Logger.getLogger(this.getClass());

	public ListResult<RAreaListBean> getAllArea(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

}
