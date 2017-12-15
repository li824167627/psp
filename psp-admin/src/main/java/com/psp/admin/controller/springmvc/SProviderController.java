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
 * 服务商相关接口
 **/
@Controller
@RequestMapping(value = "/provider", produces = "application/json")
public class SProviderController {
	@Autowired
	com.psp.admin.controller.ProviderController providerController;

	/**
	 * 创建服务商并生成管理员账户
	 **/
	@RequestMapping("/v1/add")
	@ResponseBody
	public ObjectResult<RAccountBean> addProvider(@Validated AddProviderParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RAccountBean> res = new ObjectResult<RAccountBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return providerController.addProvider(param, request, response);
	}

	/**
	 * 编辑服务商
	 **/
	@RequestMapping("/v1/eidtProvider")
	@ResponseBody
	public BaseResult eidtProvider(@Validated EditProviderParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return providerController.eidtProvider(param, request, response);
	}

	/**
	 * 添加服务商服务
	 **/
	@RequestMapping("/v1/addService")
	@ResponseBody
	public BaseResult addService(@Validated AddProviderServiceParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return providerController.addService(param, request, response);
	}

	/**
	 * 删除服务商服务
	 **/
	@RequestMapping("/v1/delService")
	@ResponseBody
	public BaseResult delService(@Validated DelProviderServiceParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return providerController.delService(param, request, response);
	}

	/**
	 * 获取服务商列表
	 **/
	@RequestMapping("/v1/getServiceList")
	@ResponseBody
	public ObjectResult<RCategoryJSONBean> getServiceList(@Validated GetProviderServiceListParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RCategoryJSONBean> res = new ObjectResult<RCategoryJSONBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return providerController.getServiceList(param, request, response);
	}

	/**
	 * 获取服务商列表
	 **/
	@RequestMapping("/v1/getList")
	@ResponseBody
	public ListResult<RProviderBean> getList(@Validated GetProviderListParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ListResult<RProviderBean> res = new ListResult<RProviderBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return providerController.getList(param, request, response);
	}

	/**
	 * 删除服务商
	 **/
	@RequestMapping("/v1/del")
	@ResponseBody
	public BaseResult delProvider(@Validated DelProviderParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return providerController.delProvider(param, request, response);
	}

	/**
	 * 获取服务商信息
	 **/
	@RequestMapping("/v1/getDetail")
	@ResponseBody
	public ObjectResult<RProviderBean> getDetail(@Validated GetProviderDetailParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<RProviderBean> res = new ObjectResult<RProviderBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return providerController.getDetail(param, request, response);
	}

	/**
	 * 获取服务商账户列表
	 **/
	@RequestMapping("/v1/getAccountList")
	@ResponseBody
	public ListResult<RAccountBean> getAccountList(@Validated GetProviderAccountListParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ListResult<RAccountBean> res = new ListResult<RAccountBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return providerController.getAccountList(param, request, response);
	}

	/**
	 * 创建服务商账户
	 **/
	@RequestMapping("/v1/addAccount")
	@ResponseBody
	public BaseResult addAccount(@Validated AddProviderAccountParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return providerController.addAccount(param, request, response);
	}

	/**
	 * 重置服务商账户密码
	 **/
	@RequestMapping("/v1/restPwd")
	@ResponseBody
	public BaseResult resetPwd(@Validated ResetProviderPwdParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return providerController.resetPwd(param, request, response);
	}

	/**
	 * 删除服务商账户
	 **/
	@RequestMapping("/v1/delAccount")
	@ResponseBody
	public BaseResult delAccount(@Validated DelProviderAccountParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return providerController.delAccount(param, request, response);
	}
}
