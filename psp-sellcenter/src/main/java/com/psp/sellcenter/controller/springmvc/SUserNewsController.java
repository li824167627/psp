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
	public ListResult<RSaleUserNewsBean> getUserNews(@Validated GetUserNewsParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ListResult<RSaleUserNewsBean> res = new ListResult<RSaleUserNewsBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return userNewsController.getUserNews(param, request, response);
	}

	/**
	 * 新建/编辑客户消息
	 **/
	@RequestMapping("/v1/edit")
	@ResponseBody
	public BaseResult edit(@Validated EditUserNewsParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return userNewsController.edit(param, request, response);
	}
}
