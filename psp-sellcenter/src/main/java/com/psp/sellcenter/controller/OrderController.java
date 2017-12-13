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
			String pid = param.getPid();
			String uid = param.getUid();
			String label = param.getLabel();
			String content = param.getContent();
			boolean flag = orderServiceImpl.addOrder(sid, pid, uid, label, content);
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
			logger.info(e);
			e.printStackTrace();
			result.setFlag(false);
			result.setMsg(e.getMessage());
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

			String sid = (String)request.getAttribute("sellerId");
			String oid = param.getOid();
			
			ROrderBean data = orderServiceImpl.getDetail(sid, oid);
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
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
			result.setFlag(false);
			result.setMsg(e.getMessage());
		}
		return result;
	}

	/**
	 * 分配工单
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResult allotOrder(AllotOrderParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {

			String sid = (String)request.getAttribute("sellerId");
			String pid = param.getPid();
			String oid = param.getOid();
			boolean flag = orderServiceImpl.allotOrder(sid, pid, oid);
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
	 * 关闭工单 -1不能分配-2不能生成合同-3项目终止
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResult closeOrder(CloseOrderParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {

			String sid = (String)request.getAttribute("sellerId");
			String oid = param.getOid();
			String content = param.getContent();
			int status = NumUtil.toInt(param.getStatus(), -1);//-1不能分配-2不能生成合同-3项目终止
			boolean flag = orderServiceImpl.closeOrder(sid, oid, content, status);
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
	 * 上传合同
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResult uploadContract(UploadContractParam param, HttpServletRequest request,
			HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {

			String sid = (String)request.getAttribute("sellerId");
			String oid = param.getOid();
			String contractNo = param.getContractNo();
			String name = param.getName();
			String signTime = param.getSignTime();
			String startTime = param.getStartTime();
			String endTime = param.getEndTime();
			String partyA = param.getPartyA();
			String partyB = param.getPartyB();
			String contractUrl = param.getContractUrl();
			int contractType = param.getType();
			int payment = NumUtil.toInt(param.getPayment(), 0);// 付款方式：0一次性 1分期
			String paymentWay = param.getPaymentWay();//分期方式JSON 
			String service = param.getService();
			String paymentDesc = param.getPayDesc();
			double money = param.getMoney() == null ? 0 :param.getMoney();
			boolean flag = orderServiceImpl.uploadContract(sid, oid, contractNo, name, signTime, startTime, 
					endTime, partyA, partyB, contractUrl, payment, paymentWay, service, money, paymentDesc, contractType);
			result.setFlag(flag);
		} catch (ServiceException e) {
			result.setServiceException(e);
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
			result.setFlag(false);
         

		}
		return result;
	}
	
	/**
	 * 确认完成
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResult confirmOrder(ConfirmOrderParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {

			String sid = (String)request.getAttribute("sellerId");
			String oid = param.getOid();
			String content = param.getContent();
			int type = NumUtil.toInt(param.getType(), 1);//1完成工单2拒绝完成
			boolean flag = orderServiceImpl.confirmOrder(sid, oid, content, type);
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
	 * 反馈
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResult feedback(FeedbackParam param, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {

			String sid = (String)request.getAttribute("sellerId");
			String oid = param.getOid();
			String content = param.getContent();
			String score = param.getScore();
			boolean flag = orderServiceImpl.feedback(sid, oid, content, score);
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
