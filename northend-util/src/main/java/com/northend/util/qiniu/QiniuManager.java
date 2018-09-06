package com.northend.util.qiniu;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

public class QiniuManager {

	Logger logger = Logger.getLogger(QiniuManager.class);

	private static QiniuManager instance;
	// 创建上传对象
	String accessKey = "MUadFVajknLWbM3LKZWZAdZ9KExWGWWAAW0cEn02";
	String secretKey = "WLdpuWE3I-93CXeAnEY3wqItfpEdHw2Awg4N2Fcm";
	String bucket = "";
	UploadManager uploadManager = null;
	Auth auth = null;

	/**
	 * 
	 * @param ak
	 * @param sk
	 * @param bk
	 *            bucket 存储空间
	 * @param zone
	 *            Zone.zone1()
	 */
	private QiniuManager(String ak, String sk, String bk, Zone zone) {
		if (ak != null) {
			this.accessKey = ak;
		}
		if (sk != null) {
			this.secretKey = sk;
		}
		if (bk != null) {
			this.bucket = bk;
		}

		auth = Auth.create(accessKey, secretKey);
		// 构造一个带指定Zone对象的配置类
		Configuration cfg = new Configuration(zone);
		uploadManager = new UploadManager(cfg);
	}

	public static QiniuManager getInstance(String accessKey, String secretKey, String bucket, Zone zone) {
		synchronized (QiniuManager.class) {
			if (instance == null) {
				instance = new QiniuManager(accessKey, secretKey, bucket, zone);
			}
			return instance;
		}
	}

	/**
	 * 获取token
	 * 
	 * @return
	 */
	public String getToken(long expireSeconds) {
		Auth auth = Auth.create(accessKey, secretKey);
		StringMap putPolicy = new StringMap();
		putPolicy.put("returnBody",
				"{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)"
						+ ",\"imgW\":$(imageInfo.width),\"imgH\":$(imageInfo.height),\"imgFormat\":$(imageInfo.format)}");

		String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
		return upToken;
	}

	/**
	 * 上传文件
	 * 
	 * @param key
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public QiniuReFile upload(String token, String key, byte[] data) {
		try {
			logger.info("bucket:" + bucket);
			// 调用put方法上传，这里指定的key和上传策略中的key要一致
			Response res = uploadManager.put(data, key, token);
			// 打印返回的信息
			logger.info("upload qiniu server response body string is: " + res.bodyString());
			if (res.isOK() && res.isJson()) {
				return JSON.parseObject(res.bodyString(), QiniuReFile.class);
			}
		} catch (QiniuException e) {
			Response res = e.response;
			// 请求失败时打印的异常信息
			try {
				// 响应的文本信息
				logger.info("upload qiniu server response body string error is: " + res.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
		}
		return null;
	}
}
