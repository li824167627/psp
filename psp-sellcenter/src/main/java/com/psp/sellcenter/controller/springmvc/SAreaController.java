package com.psp.sellcenter.controller.springmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;
import com.psp.sellcenter.controller.res.*;
import com.psp.sellcenter.controller.res.bean.*;
import com.psp.sellcenter.controller.springmvc.req.*;

/**
 * 地区相关接口
 **/
@Controller
@RequestMapping(value = "/area", produces = "application/json")
public class SAreaController {
	@Autowired
	com.psp.sellcenter.controller.AreaController areaController;

	/**
	 * 获取区域
	 **/
	@RequestMapping("/v1/getAllArea")
	@ResponseBody
	public ListResult<RAreaListBean> getAllArea(HttpServletRequest request, HttpServletResponse response) {

		return areaController.getAllArea(request, response);
	}
}
