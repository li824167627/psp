package com.psp.provider.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.psp.provider.controller.res.BaseResult;
import com.psp.provider.controller.res.ListResult;
import com.psp.provider.controller.res.ObjectResult;
import com.psp.provider.controller.res.bean.ROrderBean;
import com.psp.provider.controller.res.bean.ROrderLogsBean;
import com.psp.provider.controller.springmvc.req.AcceptOrderParam;
import com.psp.provider.controller.springmvc.req.GetOrderDetailParam;
import com.psp.provider.controller.springmvc.req.GetOrderLogsParam;
import com.psp.provider.controller.springmvc.req.GetOrderNumParam;
import com.psp.provider.controller.springmvc.req.GetOrdersParam;
import com.psp.provider.controller.springmvc.req.RefuseOrderParam;
import com.psp.provider.controller.springmvc.req.SubmitCloseParam;
import com.psp.provider.controller.springmvc.req.SubmitFinishParam;
import com.psp.provider.model.AccountBean;
import com.psp.provider.service.OrderService;
import com.psp.provider.service.exception.ServiceException;
import com.psp.provider.service.impl.res.PageResult;
import com.psp.util.NumUtil;

@Component
public class OrderController {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	OrderService orderServiceImpl;
	
	/**
	 * 获取工单列表
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public ListResult<ROrderBean> getOrders(GetOrdersParam param, HttpServletRequest request,
			HttpServletResponse response) {
		ListResult<ROrderBean> result = new ListResult<>();
		try {
			AccountBean account = (AccountBean)request.getAttribute("account");
			int page = NumUtil.toInt(param.getPage(), 0);
			int pageSize = NumUtil.toInt(param.getPagesize(), 20);
			int filteType = NumUtil.toInt(param.getFilteType(), 99);//筛选类型
			int stype = NumUtil.toInt(param.getStype(), 0);//搜索类型
			int stage = NumUtil.toInt(param.getStage(), 0);//阶段搜索
			String key = param.getKey();//关键字
			
			PageResult<ROrderBean> resList = orderServiceImpl.getOrders(account, page, pageSize, filteType, stype, key, stage);
			if(resList == null) {
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
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public ObjectResult<Integer> getOrderNum(GetOrderNumParam param, HttpServletRequest request,
			HttpServletResponse response) {
		ObjectResult<Integer> result = new ObjectResult<>();
		try {
			AccountBean account = (AccountBean)request.getAttribute("account");
			int stage = NumUtil.toInt(param.getStage(), 0);
			int userNum = orderServiceImpl.getOrderNum2Provider(account, stage);
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
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public ObjectResult<ROrderBean> getDetail(GetOrderDetailParam param, HttpServletRequest request,
			HttpServletResponse response) {
		ObjectResult<ROrderBean> result = new ObjectResult<>();
		try {

			AccountBean account = (AccountBean)request.getAttribute("account");
			String oid = param.getOid();
			
			ROrderBean data = orderServiceImpl.getDetail(account, oid);
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
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public ListResult<ROrderLogsBean> getOrderLogs(GetOrderLogsParam param, HttpServletRequest request,
			HttpServletResponse response) {
		ListResult<ROrderLogsBean> result = new ListResult<>();
		try {
			AccountBean account = (AccountBean)request.getAttribute("account");
			String oid = param.getOid();
			String key = param.getKey();//关键字
			
			PageResult<ROrderLogsBean> resList = orderServiceImpl.getOrderogs(account, oid, key);
			
			if(resList == null) {
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
	
	/**
	 * 拒绝工单
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResult refuse(RefuseOrderParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {

			AccountBean account = (AccountBean)request.getAttribute("account");
			String oid = param.getOid();
			String content = param.getContent();
			boolean flag = orderServiceImpl.refuseOrder(oid, content, account);
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
	
	/**
	 * 接收工单
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResult accept(AcceptOrderParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {

			AccountBean account = (AccountBean)request.getAttribute("account");
			String oid = param.getOid();
			String content = param.getContent();
			boolean flag = orderServiceImpl.acceptOrder(oid, content, account);
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
	
	/**
	 * 申请完成工单
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResult submitFinish(SubmitFinishParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {

			AccountBean account = (AccountBean)request.getAttribute("account");
			String oid = param.getOid();
			String content = param.getContent();
			int type = NumUtil.toInt(param.getType(), 1);//申请完成状态:1 按期完成，2 延期完成，3其他
			boolean flag = orderServiceImpl.submitFinish(oid, type, content, account);
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
	
	/**
	 * 申请终止工单
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResult submitClose(SubmitCloseParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {

			AccountBean account = (AccountBean)request.getAttribute("account");
			String oid = param.getOid();
			String content = param.getContent();
			int type = NumUtil.toInt(param.getType(), 1);//申请关闭状态:1 客户终止，2其他
			boolean flag = orderServiceImpl.submitClose(oid, type, content, account);
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
