package com.psp.util.qiniu;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

public class QiniuManager {

	String accessKey = "nh6Zdp1-hcE_i4Ae7YDIzemD9M6w795z940i56rF";
	String secretKey = "OBo_cyuVpi6O4NrpeCRfXUQiL852NR-vbKF1R_Ps";

	public QiniuManager(String accessKey, String secretKey) {
		this.accessKey = accessKey;
		this.secretKey = secretKey;
	}

	/**
	 * 获取token
	 * 
	 * @return
	 */
	public String getToken(String bucket, long expireSeconds) {
		Auth auth = Auth.create(accessKey, secretKey);
		StringMap putPolicy = new StringMap();
		putPolicy.put("returnBody",
				"{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)"
						+ ",\"imgW\":$(imageInfo.width),\"imgH\":$(imageInfo.height),\"imgFormat\":$(imageInfo.format)}");
		String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
		return upToken;
	}

	/**
	 * 上传
	 * 
	 * @param bucket
	 * @param uploadBytes
	 */
	public DefaultPutRet upload(String bucket, String key, byte[] uploadBytes) {
		// 构造一个带指定Zone对象的配置类
		Configuration cfg = new Configuration(Zone.zone0());
		// ...其他参数参考类注释
		UploadManager uploadManager = new UploadManager(cfg);
		// 默认不指定key的情况下，以文件内容的hash值作为文件名
		String upToken = getToken(bucket, 1000);
		try {
			Response response = uploadManager.put(uploadBytes, key, upToken);
			// 解析上传成功的结果
			DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			System.out.println(putRet.key);
			System.out.println(putRet.hash);
			return putRet;
		} catch (QiniuException ex) {
			Response r = ex.response;
			System.err.println(r.toString());
			try {
				System.err.println(r.bodyString());
			} catch (QiniuException ex2) {
				// ignore
			}
		}
		return null;
	}
}
