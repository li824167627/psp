package com.psp.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.psp.admin.controller.res.BaseResult;
import com.psp.admin.controller.res.ListResult;
import com.psp.admin.controller.res.ObjectResult;
import com.psp.admin.controller.res.bean.ROrderBean;
import com.psp.admin.controller.res.bean.ROrderLogsBean;
import com.psp.admin.controller.springmvc.req.GetOrderDetailParam;
import com.psp.admin.controller.springmvc.req.GetOrderLogsParam;
import com.psp.admin.controller.springmvc.req.GetOrderNumParam;
import com.psp.admin.controller.springmvc.req.GetOrdersParam;
import com.psp.admin.service.OrderService;
import com.psp.admin.service.exception.ServiceException;
import com.psp.admin.service.res.PageResult;
import com.psp.util.NumUtil;

@Component
public class OrderController {

	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	OrderService orderServiceImpl;

	/**
	 * 获取工单列表
	 * 
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public ListResult<ROrderBean> getOrders(GetOrdersParam param, HttpServletRequest request,
			HttpServletResponse response) {
		ListResult<ROrderBean> result = new ListResult<>();
		try {
			String adminId = (String) request.getAttribute("adminId");
			String targetId = param.getTargetId();
			int ttype = NumUtil.toInt(param.getTtype(), 0);
			int page = NumUtil.toInt(param.getPage(), 0);
			int pageSize = NumUtil.toInt(param.getPagesize(), 20);
			int filteType = NumUtil.toInt(param.getFilteType(), 99);// 筛选类型
			int stype = NumUtil.toInt(param.getStype(), 0);// 搜索类型
			int stage = NumUtil.toInt(param.getStage(), 0);// 阶段搜索
			String key = param.getKey();// 关键字
			int saleType = 0;
			int dataType = NumUtil.toInt(param.getDataType(), 99);

			PageResult<ROrderBean> resList = orderServiceImpl.getOrders(adminId, page, pageSize, filteType, stype, key,
					targetId, ttype, stage, saleType, dataType);
			if (resList == null) {
				result.setData(null);
				result.setTotalSize(0);
				return result;
			}
			int totalSize = resList.getCount();
			List<ROrderBean> lists = resList.getData();
			result.setData(lists);
			result.setTotalSize(totalSize);
		} catch (ServiceException e) {
			result.setServiceException(e);
		}
		return result;
	}

	/**
	 * 获取各个阶段工单数量
	 * 
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public ObjectResult<Integer> getOrderNum(GetOrderNumParam param, HttpServletRequest request,
			HttpServletResponse response) {
		ObjectResult<Integer> result = new ObjectResult<>();
		try {

			String adminId = (String) request.getAttribute("adminId");
			int stage = NumUtil.toInt(param.getStage(), 0);
			int userNum = orderServiceImpl.getOrderNum(adminId, stage);
			result.setData(userNum);
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

	/**
	 * 获取工单详情
	 * 
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public ObjectResult<ROrderBean> getDetail(GetOrderDetailParam param, HttpServletRequest request,
			HttpServletResponse response) {
		ObjectResult<ROrderBean> result = new ObjectResult<>();
		try {

			String adminId = (String) request.getAttribute("adminId");
			String oid = param.getOid();

			ROrderBean data = orderServiceImpl.getDetail(adminId, oid);
			result.setData(data);
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

	/**
	 * 获取操作工单日志列表
	 * 
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public ListResult<ROrderLogsBean> getOrderLogs(GetOrderLogsParam param, HttpServletRequest request,
			HttpServletResponse response) {
		ListResult<ROrderLogsBean> result = new ListResult<>();
		try {
			String adminId = (String) request.getAttribute("adminId");
			String oid = param.getOid();
			String key = param.getKey();// 关键字

			PageResult<ROrderLogsBean> resList = orderServiceImpl.getOrderLogs(adminId, oid, key);

			if (resList == null) {
				result.setData(null);
				result.setTotalSize(0);
				return result;
			}
			int totalSize = resList.getCount();
			List<ROrderLogsBean> lists = resList.getData();
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

	public BaseResult ImportOrders(HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {

			boolean flag = orderServiceImpl.ImportOrders(request);
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
