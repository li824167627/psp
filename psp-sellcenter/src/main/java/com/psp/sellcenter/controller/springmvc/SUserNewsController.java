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
 * 客户消息流相关接口
 **/
@Controller
@RequestMapping(value = "/usernews", produces = "application/json")
public class SUserNewsController {
	@Autowired
	com.psp.sellcenter.controller.UserNewsController userNewsController;

	/**
	 * 获取客户信息流
	 **/
	@RequestMapping("/v1/getUserNews")
	@ResponseBody
	public ListResult<RUserNewsBean> getUserNews(@Validated GetUserNewsParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ListResult<RUserNewsBean> res = new ListResult<RUserNewsBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return userNewsController.getUserNews(param, request, response);
	}

	/**
	 * 新建客户消息
	 **/
	@RequestMapping("/v1/add")
	@ResponseBody
	public BaseResult add(@Validated AddUserNewsParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return userNewsController.add(param, request, response);
	}
}
