package com.psp.provider.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.psp.provider.cache.dao.AccountCacheDao;
import com.psp.provider.controller.res.BaseResult;
import com.psp.provider.controller.res.ObjectResult;
import com.psp.provider.controller.res.bean.RImgCodeBean;
import com.psp.provider.controller.res.bean.RQiniuFileBean;
import com.psp.provider.controller.springmvc.req.getImgcodeParam;
import com.psp.provider.controller.springmvc.req.getLoginImgcodeParam;
import com.psp.provider.model.Code;
import com.psp.provider.service.QiniuService;
import com.psp.provider.service.exception.ServiceException;
import com.psp.util.AppTextUtil;
import com.psp.util.NumUtil;
import com.psp.util.VerifyCodeUtil;
@Component
public class FileController {

	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	QiniuService qiniuServiceImpl;

	@Autowired
	AccountCacheDao accountCacheImpl;

	public ObjectResult<RImgCodeBean> getImgCode(getImgcodeParam param, HttpServletRequest request,
			HttpServletResponse response) {
		ObjectResult<RImgCodeBean> result = new ObjectResult<>();
		try {
			String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
			int w = NumUtil.toInt(param.getW(), 100);
			int h = NumUtil.toInt(param.getH(), 40);
			String imgToken = "img" + AppTextUtil.getToken();
			String img = VerifyCodeUtil.outputImage(w, h, verifyCode);
			String base64img = "data:image/jpeg;base64," + img.replaceAll("\r\n", "");
			if (base64img != null) {
				RImgCodeBean data = new RImgCodeBean();
				data.setKey(imgToken);
				data.setUrl(base64img);
				Code code = new Code();
				code.setCode(verifyCode);
				code.setNum(0);
				code.setTime(System.currentTimeMillis());
				accountCacheImpl.setImgCode(imgToken, code);
				result.setData(data);
				result.setFlag(true);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public BaseResult getLoginImgCode(getLoginImgcodeParam param, HttpServletRequest request,
			HttpServletResponse response) {
		String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
		int w = NumUtil.toInt(param.getW(), 100);
		int h = NumUtil.toInt(param.getH(), 40);
		String userId = param.getUserId();
		try {
			VerifyCodeUtil.outputImage(w, h, response.getOutputStream(), verifyCode);
			Code code = accountCacheImpl.getLoginCode(userId);
			if (code == null) {
				code = new Code();
				code.setNum(5);
			}
			code.setCode(verifyCode);
			code.setTime(System.currentTimeMillis());
			accountCacheImpl.setLoginCode(userId, code);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ObjectResult<RQiniuFileBean> uploadImage(HttpServletRequest request,
			HttpServletResponse response) {
		ObjectResult<RQiniuFileBean> result = new ObjectResult<>();
		try {
			RQiniuFileBean file = qiniuServiceImpl.uploadImgFile(request);
			result.setData(file);
			result.setFlag(file != null);
		} catch (ServiceException e) {
			result.setServiceException(e);
		}
		return result;
	}

	public ObjectResult<String> getQINIUToken(HttpServletRequest request, HttpServletResponse response) {
		ObjectResult<String> result = new ObjectResult<>();

		try {
			String token = qiniuServiceImpl.getQNToken();
			result.setData(token);
			result.setFlag(token != null);
		} catch (ServiceException e) {
			result.setServiceException(e);
		}	
		return result;
	}


}
