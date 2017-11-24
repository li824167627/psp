package com.psp.admin.controller.springmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.psp.admin.controller.res.BaseResult;
import com.psp.admin.controller.res.ObjectResult;
import com.psp.admin.controller.res.bean.RAccountBean;
import com.psp.admin.controller.res.bean.RAdminBean;
import com.psp.admin.controller.res.bean.RCategroyBean;
import com.psp.admin.controller.springmvc.req.AddProviderParam;
import com.psp.admin.controller.springmvc.req.GetProviderListParam;
import com.psp.admin.controller.springmvc.req.UpdateNameParam;

/**
 * 服务商相关接口
 **/
@Controller
@RequestMapping(value = "/provider", produces = "application/json")
public class SProviderController {
	@Autowired
	com.psp.admin.controller.ProviderController providerController;

	/**
	 * 获取服务分类列表
	 **/
	@RequestMapping("/v1/getCategories")
	@ResponseBody
	public ObjectResult<RCategroyBean> getCategories(HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RCategroyBean> res = new ObjectResult<RCategroyBean>();
		res.setData(new RCategroyBean().getDemoValue());
		return res;
	}

	/**
	 * 创建服务商并生成管理员账户
	 **/
	@RequestMapping("/v1/addProvider")
	@ResponseBody
	public ObjectResult<RAccountBean> addProvider(@Validated AddProviderParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RAccountBean> res = new ObjectResult<RAccountBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}
		res.setData(new RAccountBean().getDemoValue());
		return res;
	}

	/**
	 * 获取管理员信息
	 **/
	@RequestMapping("/v1/getList")
	@ResponseBody
	public ObjectResult<RAdminBean> getList(@Validated GetProviderListParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RAdminBean> res = new ObjectResult<RAdminBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}
		res.setData(new RAdminBean().getDemoValue());
		return res;
	}

	/**
	 * 创建服务商账户
	 **/
	@RequestMapping("/v1/addAccount")
	@ResponseBody
	public BaseResult addAccount(@Validated UpdateNameParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
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
