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
 * 销售相关接口
 **/
@Controller
@RequestMapping(value = "/seller", produces = "application/json")
public class SSellController {
	@Autowired
	com.psp.sellcenter.controller.SellController sellController;

	/**
	 * 登录
	 **/
	@RequestMapping("/v1/login")
	@ResponseBody
	public ObjectResult<RSellerBean> login(@Validated LoginParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RSellerBean> res = new ObjectResult<RSellerBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}
		res.setData(new RSellerBean().getDemoValue());
		return res;
	}

	/**
	 * 获取销售信息
	 **/
	@RequestMapping("/v1/getSeller")
	@ResponseBody
	public ObjectResult<RSellerBean> getSeller(@Validated GetSellerParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RSellerBean> res = new ObjectResult<RSellerBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}
		res.setData(new RSellerBean().getDemoValue());
		return res;
	}

	/**
	 * 更新用户名
	 **/
	@RequestMapping("/v1/updateName")
	@ResponseBody
	public BaseResult updateName(@Validated UpdateNameParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}
		res.setMsg(null);
		return res;
	}

	/**
	 * 更改密码
	 **/
	@RequestMapping("/v1/updatePassWord")
	@ResponseBody
	public BaseResult updatePassWord(@Validated UpdatePasswordParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}
		res.setMsg(null);
		return res;
	}
}
