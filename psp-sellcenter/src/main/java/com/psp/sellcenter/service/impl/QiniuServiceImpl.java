package com.psp.sellcenter.service.impl;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.psp.sellcenter.cache.dao.QiniuCache;
import com.psp.sellcenter.controller.res.bean.RQiniuFileBean;
import com.psp.sellcenter.service.QiniuService;
import com.psp.sellcenter.service.exception.ServiceException;
import com.psp.util.AppTextUtil;
import com.psp.util.qiniu.QiniuManager;
import com.psp.util.qiniu.QiniuReFile;
import com.qiniu.common.Zone;

@Service
public class QiniuServiceImpl implements QiniuService {
	Logger logger = Logger.getLogger(QiniuServiceImpl.class);

	final String accessKey = "MUadFVajknLWbM3LKZWZAdZ9KExWGWWAAW0cEn02";
	final String secretKey = "WLdpuWE3I-93CXeAnEY3wqItfpEdHw2Awg4N2Fcm";
	Zone zone = Zone.zone1();
	final String bucket = "publick";
	final String qiniulinkurl = "http://os4z3g2v6.bkt.clouddn.com/";

	QiniuManager qiniuManager = QiniuManager.getInstance(accessKey, secretKey, bucket, zone);

	@Autowired
	QiniuCache qiniuCacheImpl;

	@Override
	public String getQNToken() {
		return getQiniuToken();
	}

	/**
	 * 获取七牛 token
	 * 
	 * @return
	 */
	private String getQiniuToken() {
		long expireSeconds = 3600;
		String token = qiniuCacheImpl.getToken();
		if (token == null) {
			token = qiniuManager.getToken(expireSeconds);
			qiniuCacheImpl.setToken(token, expireSeconds - 200);
		}
		return token;
	}

	@Override
	public RQiniuFileBean uploadImgFile(HttpServletRequest request) {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());

		// 不是文件
		if (!multipartResolver.isMultipart(request)) {
			throw new ServiceException("param_is_error", "文件");
		}
		// 转换成多部分request
		MultipartHttpServletRequest multRequest = multipartResolver.resolveMultipart(request);
		// 文件类型限制
		String[] allowedType = { "image/bmp", "image/gif", "image/jpeg", "image/png" };
		byte[] value;
		try {
			// 取得request中的所有文件名
			MultipartFile file = multRequest.getFile("file");   
            int pre = (int) System.currentTimeMillis();  
            //取得上传文件  
            if(file != null){  
            		logger.info("文件" + JSON.toJSONString(file));
    				logger.info("文件类型" + file.getContentType());
    				boolean allowed = Arrays.asList(allowedType).contains(file.getContentType());
//	    				if (!allowed) {
//	    					throw new ServiceException("fileType_not_allow", file.getContentType());
//	    				}

    				// 图片大小限制
    				if (file.getSize() > 104857600) { // 100M
    					throw new ServiceException("file_too_large");
    				}
    				// 包含原始文件名的字符串
    				String fi = file.getOriginalFilename();
    				// 提取文件拓展名
    				String fileNameExtension = fi.substring(fi.indexOf("."), fi.length());
    				// 生成云端的真实文件名
    				String remoteFileName = AppTextUtil.getFileKey() + fileNameExtension;
    				value = file.getBytes();
    				String qiniuToken = getQiniuToken();
    				QiniuReFile qinuFile = qiniuManager.upload(qiniuToken, remoteFileName, value);
    				if (qinuFile != null) {
    					RQiniuFileBean data = new RQiniuFileBean();
    					data.setKey(qinuFile.getKey());
    					data.setImgH(qinuFile.getImgH());
    					data.setImgW(qinuFile.getImgW());
    					data.setUrl(qiniulinkurl + qinuFile.getKey());

    					return data;
    				}
                //记录上传该文件后的时间  
                int finaltime = (int) System.currentTimeMillis();  
                System.out.println("上传用时：" + (finaltime - pre));  
			}

		} catch (IOException e) {
			throw new ServiceException("param_is_error", "上传文件");
		}
		return null;
	}
}
