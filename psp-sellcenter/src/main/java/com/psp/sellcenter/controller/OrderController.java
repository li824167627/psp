package com.psp.sellcenter.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.psp.sellcenter.controller.res.BaseResult;
import com.psp.sellcenter.controller.res.ListResult;
import com.psp.sellcenter.controller.res.ObjectResult;
import com.psp.sellcenter.controller.res.bean.ROrderBean;
import com.psp.sellcenter.controller.res.bean.ROrderLogsBean;
import com.psp.sellcenter.controller.res.bean.RServiceProviderBean;
import com.psp.sellcenter.controller.springmvc.req.AddOrderParam;
import com.psp.sellcenter.controller.springmvc.req.AllotOrderParam;
import com.psp.sellcenter.controller.springmvc.req.CloseOrderParam;
import com.psp.sellcenter.controller.springmvc.req.ConfirmOrderParam;
import com.psp.sellcenter.controller.springmvc.req.FeedbackParam;
import com.psp.sellcenter.controller.springmvc.req.GetOrderDetailParam;
import com.psp.sellcenter.controller.springmvc.req.GetOrderLogsParam;
import com.psp.sellcenter.controller.springmvc.req.GetOrderNumParam;
import com.psp.sellcenter.controller.springmvc.req.GetOrdersParam;
import com.psp.sellcenter.controller.springmvc.req.UploadContractParam;
import com.psp.sellcenter.service.OrderService;
import com.psp.sellcenter.service.exception.ServiceException;
import com.psp.sellcenter.service.res.PageResult;
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
			String sid = (String)request.getAttribute("sellerId");
			String uid = param.getUid();
			int page = NumUtil.toInt(param.getPage(), 0);
			int pageSize = NumUtil.toInt(param.getPagesize(), 20);
			int filteType = NumUtil.toInt(param.getFilteType(), 0);//筛选类型
			int stype = NumUtil.toInt(param.getStype(), 0);//搜索类型
			int stage = NumUtil.toInt(param.getStage(), 0);//阶段搜索
			String key = param.getKey();//关键字
			
			PageResult<ROrderBean> resList = orderServiceImpl.getOrders(sid, page, pageSize, filteType, stype, key, uid, stage);
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
	 * 新建工单
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResult add(AddOrderParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {

			String sid = (String)request.getAttribute("sellerId");
			String provider = param.getProvider();
			String pid = param.getPid();
			String uid = param.getUid();
			String label = param.getLabel();
			String content = param.getContent();
			boolean flag = orderServiceImpl.addOrder(sid, pid, provider, uid, label, content);
			result.setFlag(flag);
		} catch (ServiceException e) {
			result.setServiceException(e);
		} catch (Exception e) {
			logger.info(e);
			result.setFlag(false);
			result.setMsg(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 派单时选择服务商
	 * @param request
	 * @param response
	 * @return
	 */
	public ObjectResult<RServiceProviderBean> getServiceProviders(HttpServletRequest request,
			HttpServletResponse response) {
		ObjectResult<RServiceProviderBean> result = new ObjectResult<>();
		try {
			RServiceProviderBean bean = orderServiceImpl.getServiceProviders();
			if (bean != null) {
				result.setData(bean);
			}
		} catch (ServiceException e) {
			result.setFlag(false);
			result.setMsg(e.getServiceMsg());
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

			String sid = (String)request.getAttribute("sellerId");
			int stage = NumUtil.toInt(param.getStage(), 0);
			int userNum = orderServiceImpl.getOrderNum2Seller(sid, stage);
			result.setData(userNum);
		} catch (ServiceException e) {
			result.setServiceException(e);
		} catch (Exception e) {
			logger.info(e);
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

			String sid = (String)request.getAttribute("sellerId");
			String uid = param.getOid();
			
			ROrderBean data = orderServiceImpl.getDetail(sid, uid);
			result.setData(data);
		} catch (ServiceException e) {
			result.setServiceException(e);
		} catch (Exception e) {
			logger.info(e);
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
			String sid = (String)request.getAttribute("sellerId");
			String oid = param.getOid();
			String key = param.getKey();//关键字
			
			PageResult<ROrderLogsBean> resList = orderServiceImpl.getOrderogs(sid, oid, key);
			
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
		}
		return result;
	}

	public BaseResult allotOrder(AllotOrderParam param, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public BaseResult closeOrder(CloseOrderParam param, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public BaseResult uploadContract(UploadContractParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public BaseResult confirmOrder(ConfirmOrderParam param, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public BaseResult feedback(FeedbackParam param, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
