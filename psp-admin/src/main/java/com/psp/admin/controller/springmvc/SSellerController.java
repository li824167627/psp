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
 * 销售相关接口
 **/
@Controller
@RequestMapping(value = "/seller", produces = "application/json")
public class SSellerController {
	@Autowired
	com.psp.admin.controller.SellerController sellerController;

	/**
	 * 获取销售人员列表
	 **/
	@RequestMapping("/v1/getList")
	@ResponseBody
	public ListResult<RSellerBean> getList(@Validated GetSellersParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		ListResult<RSellerBean> res = new ListResult<RSellerBean>();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return sellerController.getList(param, request, response);
	}

	/**
	 * 编辑销售
	 **/
	@RequestMapping("/v1/eidt")
	@ResponseBody
	public BaseResult eidt(@Validated EditSellerParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return sellerController.eidt(param, request, response);
	}

	/**
	 * 重置销售账户密码
	 **/
	@RequestMapping("/v1/restPwd")
	@ResponseBody
	public BaseResult resetPwd(@Validated ResetSellerPwdParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return sellerController.resetPwd(param, request, response);
	}

	/**
	 * 删除销售账户
	 **/
	@RequestMapping("/v1/del")
	@ResponseBody
	public BaseResult del(@Validated DelSellerParam param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {
		BaseResult res = new BaseResult();
		if (error.hasErrors()) {
			res.setRescode(BaseResult.param.getCode());
			res.setMsg(error.getFieldError().getDefaultMessage());
			return res;
		}

		return sellerController.del(param, request, response);
	}
}
