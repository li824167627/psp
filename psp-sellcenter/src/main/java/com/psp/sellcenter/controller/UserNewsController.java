package com.psp.sellcenter.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.northend.api.go.util.StringUtil;
import com.psp.sellcenter.controller.res.BaseResult;
import com.psp.sellcenter.controller.res.ListResult;
import com.psp.sellcenter.controller.res.bean.RUserNewsBean;
import com.psp.sellcenter.controller.springmvc.req.AddUserNewsParam;
import com.psp.sellcenter.controller.springmvc.req.GetUserNewsParam;
import com.psp.sellcenter.service.UserNewsService;
import com.psp.sellcenter.service.exception.ServiceException;
import com.psp.sellcenter.service.res.PageResult;
import com.psp.util.NumUtil;

@Component
public class UserNewsController {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	
	@Autowired
	UserNewsService userNewsServiceImpl;
	
	/**
	 * 获取客户消息流
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public ListResult<RUserNewsBean> getUserNews(GetUserNewsParam param, HttpServletRequest request,
			HttpServletResponse response) {
		ListResult<RUserNewsBean> result = new ListResult<>();
		try {
			String sid = (String)request.getAttribute("sellerId");
			String uid = param.getUid();
			int page = NumUtil.toInt(param.getPage(), 0);
			int pageSize = NumUtil.toInt(param.getPagesize(), 20);
			int stype = NumUtil.toInt(param.getStype(), 0);//搜索类型
			String key = param.getKey();//关键字
			
			PageResult<RUserNewsBean> resList = userNewsServiceImpl.getUserNews(sid, page, pageSize, stype, key, uid);
			if(resList == null) {
				result.setData(null);
				result.setTotalSize(0);
				return result;
			}
			int totalSize = resList.getCount();
			List<RUserNewsBean> lists = resList.getData();
			result.setData(lists);
			result.setTotalSize(totalSize);
		} catch (ServiceException e) {
			result.setServiceException(e);
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
			result.setFlag(false);
			result.setMsg(e.getMessage());
		}
		return result;
	}

	public BaseResult add(AddUserNewsParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {

			String sid = (String)request.getAttribute("sellerId");
			String uid = !StringUtil.isEmpty(param.getUid()) ? param.getUid() : null;
			String label = param.getLabel();
			String content = param.getContent();
			
			boolean flag = userNewsServiceImpl.add(sid, uid, label, content);
			result.setFlag(flag);
		} catch (ServiceException e) {
			result.setServiceException(e);
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
			result.setFlag(false);
			result.setMsg(e.getMessage());
		}
		return result;
	}

}
