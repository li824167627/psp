package com.psp.admin.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.psp.admin.controller.res.bean.RUserBean;
import com.psp.admin.controller.res.bean.RUserLogsBean;
import com.psp.admin.controller.res.bean.RUserNewsBean;
import com.psp.admin.model.AdminBean;
import com.psp.admin.model.SellerBean;
import com.psp.admin.model.UserBean;
import com.psp.admin.model.UserLogBean;
import com.psp.admin.model.UserNewsBean;
import com.psp.admin.model.excel.UserInfoBean;
import com.psp.admin.persist.dao.AdminDao;
import com.psp.admin.persist.dao.SellerDao;
import com.psp.admin.persist.dao.UserDao;
import com.psp.admin.persist.dao.UserLogDao;
import com.psp.admin.persist.dao.UserNewsDao;
import com.psp.admin.service.UserService;
import com.psp.admin.service.exception.ServiceException;
import com.psp.admin.service.res.PageResult;
import com.psp.util.AppTextUtil;
import com.psp.util.StringUtil;
import com.psp.util.excel.ImportExcel;

@Service
public class UserServiceImpl implements UserService {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	AdminDao adminImpl;

	@Autowired
	SellerDao sellerImpl;
	
	@Autowired
	UserDao userImpl;
	
	@Autowired
	UserLogDao userLogImpl;

	@Autowired
	UserNewsDao userNewsImpl;

	@Override
	public PageResult<RUserBean> getUsers(int page, int pageSize, int filteType, int stype, String key, int isALlot, String sid, String adminId) {
		PageResult<RUserBean> result = new PageResult<RUserBean>();
		AdminBean admin = adminImpl.selectOneById(adminId);
		String parkId = null;
		if(admin.getType() == 0) {
			parkId = admin.getPid();
		}
		int count = userImpl.selectUserCount(filteType, stype, key, isALlot, sid, parkId);
		if(count == 0) {
			return null;
		}
		List<UserBean> resList = userImpl.selectUsers(page, pageSize, filteType, stype, key, isALlot, sid, parkId);
		List<RUserBean> resData = new ArrayList<>();
		if (resList != null && resList.size() > 0) {
			for (UserBean bean : resList) {
				RUserBean rb = parse(bean);
				resData.add(rb);
			}
		}
		result.setCount(count);
		result.setData(resData);
		return result;
	}	
	
	/**
	 * 格式化数据
	 * @param user
	 * @return
	 */
	private RUserBean parse(UserBean user) {
		RUserBean res = new RUserBean();
		res.setName(user.getName());
		res.setAdminJson(user.getAdminJson());
		res.setAid(user.getAid());
		if(user.getAllotTime() != null) {
			res.setAllotTime(user.getAllotTime().getTime() / 1000);
		}
		res.setCompanyName(user.getCompanyName());
		if(user.getCreateTime() != null) {
			res.setCreateTime(user.getCreateTime().getTime() / 1000);
		}
		res.setIsAllot(user.getIsAllot());
		res.setLabel(user.getLabel());
		res.setLevel(user.getLevel());
		res.setOrderNum(user.getOrderNum());
		res.setOrigin(user.getOrigin());
		res.setPhoneNum(user.getPhoneNum());
		res.setPosition(user.getPosition());
		res.setCreaterJson(user.getSellerJson());
		if(user.getSeller() != null) {
			SellerBean selBean = user.getSeller();
			JSONObject sellerJson = new JSONObject();
			sellerJson.put("name", selBean.getUsername());
			sellerJson.put("phone", selBean.getPhoneNum());
			res.setSellerJson(sellerJson.toJSONString());
		}
		res.setSid(user.getSid());
		res.setUid(user.getUid());
		res.setStatus(user.getStatus());
		res.setType(user.getType());
		return res;
	}

	@Override
	public RUserBean getDetail(String aid, String uid) {
		UserBean user = userImpl.selectUserById(uid);
		if(user == null) {
			throw new ServiceException("object_is_not_exist", "客户");
		}
		return parse(user);
	}

	@Override
	public PageResult<RUserLogsBean> getUserLogs(String aid, String uid, String key) {
		PageResult<RUserLogsBean> result = new PageResult<RUserLogsBean>();
		int count = userLogImpl.selectUserLogsCount(uid, key);
		if(count == 0) {
			return null;
		}
		List<UserLogBean> resList = userLogImpl.selectUserLogs(uid, key);
		List<RUserLogsBean> resData = new ArrayList<>();
		if (resList != null && resList.size() > 0) {
			for (UserLogBean bean : resList) {
				RUserLogsBean rb = parse(bean);
				resData.add(rb);
			}
		}
		result.setCount(count);
		result.setData(resData);
		return result;
	}

	@Override
	public PageResult<RUserNewsBean> getUserNews(String sid, int page, int pageSize, int stype, String key,
			String uid) {
		PageResult<RUserNewsBean> result = new PageResult<RUserNewsBean>();
		int count = userNewsImpl.selectUserNewsCount(null, stype, key, uid);
		if(count == 0) {
			return null;
		}
		List<UserNewsBean> resList = userNewsImpl.selectUserNews(page, pageSize, null, stype, key, uid);
		List<RUserNewsBean> resData = new ArrayList<>();
		if (resList != null && resList.size() > 0) {
			for (UserNewsBean bean : resList) {
				RUserNewsBean rb = parse(bean);
				resData.add(rb);
			}
		}
		result.setCount(count);
		result.setData(resData);
		return result;
	}
	
	/**
	 * 格式化数据
	 * @param bean
	 * @return
	 */
	private RUserNewsBean parse(UserNewsBean bean) {
		RUserNewsBean res = new RUserNewsBean();
		res.setContent(bean.getContent());
		if(bean.getCreateTime() != null) {
			res.setCreateTime(bean.getCreateTime().getTime() / 1000);
		}
		res.setLabel(bean.getLabel());
		res.setNid(bean.getNid());
		res.setOrigin(bean.getOrigin());
		res.setUid(bean.getUid());
		res.setUserJson(bean.getUserJson());
		res.setSid(bean.getSid());
		res.setSellerJson(bean.getSellerJson());
		return res;
	}
	
	/**
	 * 插入客户操作日志
	 * @param type 操作类型
	 * @param sid 销售id
	 * @param sellerName 销售名称
	 * @param sellerJson 销售json
	 * @param user 用户信息
	 * @param aid 管理员ID
	 * @param adminName 管理员名称
	 * @param adminJson 管理员json
	 * @return
	 */
	private boolean insertUserLog(int type, String sid, String sellerName, String sellerJson, 
			UserBean user, String aid, String adminName, String adminJson) {
		UserLogBean userlog = new UserLogBean();
		userlog.setUid(user.getUid());
		if(!StringUtil.isEmpty(sid)) {
			userlog.setSid(sid);
			userlog.setSellerJson(sellerJson);
		}
		if(!StringUtil.isEmpty(aid)) {
			userlog.setAid(aid);
			userlog.setAdminJson(adminJson);
		}
		// 0管理员分配 1 新建客户 2 修改客户 3归档客户
		JSONObject userJson = new JSONObject();
		userJson.put("name", user.getName());
		userJson.put("phoneNum", user.getPhoneNum());
		userJson.put("companyName", user.getCompanyName());
		userJson.put("position", user.getPosition());
		userlog.setContent(userJson.toJSONString());
		userlog.setType(type);
		return userLogImpl.insert(userlog) > 0;
		
	}
	
	
	/**
	 * 格式化客户操作日志
	 * @param bean
	 * @return
	 */
	private RUserLogsBean parse(UserLogBean bean) {
		RUserLogsBean userlog = new RUserLogsBean();
		userlog.setUid(bean.getUid());
		userlog.setSid(bean.getSid());
		userlog.setSellerJson(bean.getSellerJson());
		userlog.setLid(bean.getLid());
		userlog.setAid(bean.getAid());
		userlog.setAdminJson(bean.getAdminJson());
		userlog.setContent(bean.getContent());
		userlog.setType(bean.getType());
		if(bean.getCreateTime() != null) {
			userlog.setCreateTime(bean.getCreateTime().getTime() / 1000);
		}
		return userlog;
	}

	@Override
	public int getUserNum(int isAllot, String adminId) {
		//TODO:存入缓存 客户数量存入缓存
		AdminBean admin = adminImpl.selectOneById(adminId);
		String parkId = null;
		if(admin.getType() == 0) {
			parkId = admin.getPid();
		}
		int count = userImpl.selectUserCount(0, 0, null, isAllot, null, parkId);
		return count;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean allot(String aid, String sid, String uid) {
		AdminBean admin = adminImpl.selectOneById(aid);
		UserBean user = userImpl.selectUserById(uid);
		if(user == null) {
			throw new ServiceException("object_is_not_exist", "被分配的客户");
		}
		if(user.getIsAllot() == 1) {
			throw new ServiceException("user_has_alloted");
		}
		JSONObject adminJson = new JSONObject();
		adminJson.put("name", admin.getUsername());
		adminJson.put("phone", admin.getPhoneNum());
		SellerBean seller = sellerImpl.selectOneById(sid);
		boolean flag = false;
		if(seller == null) {
			throw new ServiceException("object_is_not_exist", "分配的销售");
		}
		JSONObject sellerJson = new JSONObject();
		sellerJson.put("name", seller.getUsername());
		sellerJson.put("phone", seller.getPhoneNum());
		user.setSid(sid);
		user.setSellerJson(sellerJson.toJSONString());
		user.setAid(aid);
		user.setAdminJson(adminJson.toJSONString());
		user.setIsAllot(1);// 已分配
		flag = userImpl.allotUser(user) > 0;
		if(!flag) {
			throw new ServiceException("update_user_error");
		}
		flag = insertUserLog(0, sid, seller.getUsername(), sellerJson.toJSONString(), user, aid, admin.getUsername(), adminJson.toJSONString());
		if(!flag) {
			throw new ServiceException("create_user_log_error");
		}
		return flag;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean ImportUsers(HttpServletRequest request) throws Exception {
		boolean flag = false;
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 不是文件
		if (!multipartResolver.isMultipart(request)) {
			throw new ServiceException("param_is_error", "文件");
		}
		// 转换成多部分request
		MultipartHttpServletRequest multRequest = (MultipartHttpServletRequest) request;
		File excelfile = null;
		try {
			// 获得文件：   
	        MultipartFile file = multRequest.getFile("file");   
	        excelfile = File.createTempFile("tmp", null);
	        // 读取excel内容
	        ImportExcel<UserInfoBean> test = new ImportExcel<UserInfoBean>(UserInfoBean.class);  
	      
	        file.transferTo(excelfile);      
	        Long befor = System.currentTimeMillis();  
	        List<UserInfoBean> result = (ArrayList<UserInfoBean>) test.importExcel(excelfile);  
	        Long after = System.currentTimeMillis();  
	        List<UserBean> users = new ArrayList<>();
	        
	        if(result != null && result.size() > 0) {
	        		for(UserInfoBean u: result) {
	        			UserBean user = new UserBean();
	        			user.setUid(AppTextUtil.getPrimaryKey());
	        			user.setCompanyName(u.getCompany());
	        			user.setIsAllot(1);// 已分配
	        			user.setLevel(1);// 有效
	        			user.setName(u.getUser());
	        			user.setOrigin(2);//	线下
	        			user.setSid(u.getSeller());
	        			user.setStatus(1);// 已沟通
	        			user.setType(2);// 补录数据
	        			users.add(user);
	        		}
	        		if(users.size() > 0) {
	        			flag = userImpl.insertUsers(users) > 0;
	        			if(!flag) {
	        				throw new ServiceException("import_excel_error");
	        			}
	        		}
	        		
	        }
	        
	        System.out.println("此次操作共耗时：" + (after - befor) + "毫秒");  
	        excelfile.deleteOnExit();
			
		} catch (Exception e) {
			throw e;
		}   
		return flag;
	}


}
