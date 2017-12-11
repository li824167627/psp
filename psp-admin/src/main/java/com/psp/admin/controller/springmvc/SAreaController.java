package com.psp.admin.controller.springmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;
import com.psp.admin.controller.res.*;
import com.psp.admin.controller.res.bean.*;
import com.psp.admin.controller.springmvc.req.*;

/**
 * 文件相关接口
 **/
@Controller
@RequestMapping(value = "/area", produces = "application/json")
public class SAreaController {
	@Autowired
	com.psp.admin.controller.AreaController areaController;

	/**
	 * 获取区域
	 **/
	@RequestMapping("/v1/getAllArea")
	@ResponseBody
	public ObjectResult<RAreaListBean> getAllArea(HttpServletRequest request, HttpServletResponse response) {

		return areaController.getAllArea(request, response);
	}
}
