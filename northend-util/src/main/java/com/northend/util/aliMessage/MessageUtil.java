package com.northend.util.aliMessage;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class MessageUtil {

    // 产品名称:云通信短信API产品,开发者无需替换
    private static final String product = "Dysmsapi";
    // 产品域名,开发者无需替换
    private static final String domain = "dysmsapi.aliyuncs.com";

    // 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    private static String accessKeyId = "accessKeyId";
    private static String accessKeySecret = "accessKeySecret";
    private static String signName = "signName";
    private static String identifyingTempleteCode = "SMS_113970054";
    private static String authSuccessTempleteCode = "SMS_120406185";
    private static String authFailTempleteCode = "SMS_120376223";
    private static String authSecSuccessTempleteCode = "SMS_120376222";
    private static String authSecFailTempleteCode = "SMS_120406184";
    private static String allotSecSuccessTempleteCode = "SMS_120376319";
    private static String authSecRemovePlotTempleteCode = "SMS_120406271";
    private static String authAddrSuccessTempleteCode = "SMS_121910506";
    private static String authAddrFailTempleteCode = "SMS_121850493";
    private static String visitReqTempleteCode = "SMS_121850495";
    private static String visitSuccessTempleteCode = "SMS_121905467";
    private static String visitFailTempleteCode = "SMS_121850513";
    private static String inviteReqTempleteCode = "SMS_121850520";
    private static String crossGateTempleteCode = "SMS_121910542";
    private static String unCrossGateTempleteCode = "SMS_122288136";
    private static String addContactTempleteCode = "SMS_121850517";
    private static String agreeContactTempleteCode = "SMS_121910544";
    

    public static void init(String accessKeyId, String accessKeySecret, String signName) {
        MessageUtil.accessKeyId = accessKeyId;
        MessageUtil.accessKeySecret = accessKeySecret;
        MessageUtil.signName = signName;
    }

    public static SendSmsResponse sendSms(String mobile, String templateParam, String templateCode)
            throws ClientException {

        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();

        // 必填:待发送手机号
        request.setPhoneNumbers(mobile);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);

        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(templateParam);

        // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCode("90997");

        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        // hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }
    
    /**
     * 发送验证码
     * @param mobile
     * @param code
     * @return
     */
    public static SendSmsResponse sendIdentifyingCode(String mobile, String code) {
        try {
            return sendSms(mobile, "{\"code\":\"" + code + "\"}", identifyingTempleteCode);
        } catch (ClientException e) {
            e.printStackTrace();
        }
		return null;
    }
    	
    /**
     * 实名认证成功
     * @param mobile
     * @param signName
     * @return
     */
    public static SendSmsResponse sendUserAuthSuccess(String mobile, String signName) {
        try {
            return sendSms(mobile, null, authSuccessTempleteCode);
        } catch (ClientException e) {
            e.printStackTrace();
        }
		return null;
    }
    
    /**
     * 实名认证失败
     * @param mobile
     * @param signName
     * @param reason
     * @return
     */
    public static SendSmsResponse sendUserAuthFail(String mobile, String signName, String reason) {
        try {
            return sendSms(mobile, "{\"reason\":\"" + reason + "\"}", authFailTempleteCode);
        } catch (ClientException e) {
            e.printStackTrace();
        }
		return null;
    }
    
    /**
     * 保安认证成功
     * @param mobile
     * @param signName
     * @return
     */
    public static SendSmsResponse sendSecAuthSuccess(String mobile) {
        try {
            return sendSms(mobile, null, authSecSuccessTempleteCode);
        } catch (ClientException e) {
            e.printStackTrace();
        }
		return null;
    }
    
    /**
     * 保安认证失败
     * @param mobile
     * @param signName
     * @param reason
     * @return
     */
    public static SendSmsResponse sendSecAuthFail(String mobile, String reason) {
        try {
            return sendSms(mobile, "{\"reason\":\"" + reason + "\"}", authSecFailTempleteCode);
        } catch (ClientException e) {
            e.printStackTrace();
        }
		return null;
    }
    
    /**
     * 保安分配成功
     * @param mobile
     * @param signName
     * @return
     */
    public static SendSmsResponse sendSecAllotSuccess(String mobile, String plot, String box) {
        try {
            return sendSms(mobile, "{\"plotName\":\"" + plot + "\",\"boxName\":\"" + box + "\"}", allotSecSuccessTempleteCode);
        } catch (ClientException e) {
            e.printStackTrace();
        }
		return null;
    }
    
    /**
     * 从小区移除保安
     * @param phone
     * @return
     */
	public static SendSmsResponse sendSecRemovePlot(String mobile) {
		 try {
	            return sendSms(mobile, null, authSecRemovePlotTempleteCode);
	        } catch (ClientException e) {
	            e.printStackTrace();
	        }
			return null;
	}
	
	
	
	 /**
     * 地址认证成功
     * @param mobile
     * @param signName
     * @return
     */
    public static SendSmsResponse sendAddrAuthSuccess(String mobile) {
        try {
            return sendSms(mobile, null, authAddrSuccessTempleteCode);
        } catch (ClientException e) {
            e.printStackTrace();
        }
		return null;
    }
    
    /**
     * 地址认证失败
     * @param mobile
     * @param signName
     * @param reason
     * @return
     */
    public static SendSmsResponse sendAddrAuthFail(String mobile, String reason) {
        try {
            return sendSms(mobile, "{\"reason\":\"" + reason + "\"}", authAddrFailTempleteCode);
        } catch (ClientException e) {
            e.printStackTrace();
        }
		return null;
    }
    
    /**
     * 拜访请求
     * @param mobile
     * @param signName
     * @return
     */
    public static SendSmsResponse sendVisitReq(String mobile, String userName) {
        try {
            return sendSms(mobile, "{\"userName\":\"" + userName + "\"}", visitReqTempleteCode);
        } catch (ClientException e) {
            e.printStackTrace();
        }
		return null;
    }
    
    /**
     * 拜访请求通过
     * @param mobile
     * @param signName
     * @param reason
     * @return
     */
    public static SendSmsResponse sendVisitSuccess(String mobile, String userName, String plot) {
        try {
            return sendSms(mobile, "{\"userName\":\"" + userName + "\",\"plot\":\"" + plot + "\"}", visitSuccessTempleteCode);
        } catch (ClientException e) {
            e.printStackTrace();
        }
		return null;
    }
    
    /**
     * 拜访请求通过
     * @param mobile
     * @param signName
     * @param reason
     * @return
     */
    public static SendSmsResponse sendVisitFail(String mobile, String userName) {
        try {
            return sendSms(mobile, "{\"userName\":\"" + userName + "\"}", visitFailTempleteCode);
        } catch (ClientException e) {
            e.printStackTrace();
        }
		return null;
    }
    /**
     * 来访邀请
     * @param mobile
     * @param signName
     * @param reason
     * @return
     */
    public static SendSmsResponse sendInviteReq(String mobile, String userName, String plot) {
        try {
            return sendSms(mobile, "{\"userName\":\"" + userName + "\",\"plot\":\"" + plot + "\"}", inviteReqTempleteCode);
        } catch (ClientException e) {
            e.printStackTrace();
        }
		return null;
    }
    
    
    /**
     * 通过门禁
     * @param mobile
     * @param signName
     * @param reason
     * @return
     */
    public static SendSmsResponse sendCrossGate(String mobile, String userName, String plot, String box) {
        try {
            return sendSms(mobile, "{\"userName\":\"" + userName + "\",\"plot\":\"" + plot + "\",\"box\":\"" + box + "\"}", crossGateTempleteCode);
        } catch (ClientException e) {
            e.printStackTrace();
        }
		return null;
    }
    
    /**
     * 未通过门禁
     * @param mobile
     * @param signName
     * @param reason
     * @return
     */
    public static SendSmsResponse sendUnCrossGate(String mobile, String userName, String plot, String box) {
        try {
            return sendSms(mobile, "{\"userName\":\"" + userName + "\",\"plot\":\"" + plot + "\",\"box\":\"" + box + "\"}", unCrossGateTempleteCode);
        } catch (ClientException e) {
            e.printStackTrace();
        }
		return null;
    }
    
    
    /**
     * 好友申请
     * @param mobile
     * @param signName
     * @return
     */
    public static SendSmsResponse sendAddConactReq(String mobile, String userName) {
        try {
            return sendSms(mobile, "{\"userName\":\"" + userName + "\"}", addContactTempleteCode);
        } catch (ClientException e) {
            e.printStackTrace();
        }
		return null;
    }
    
    /**
     * 好友同意
     * @param mobile
     * @param signName
     * @return
     */
    public static SendSmsResponse sendAgreeContactReq(String mobile, String userName) {
        try {
            return sendSms(mobile, "{\"userName\":\"" + userName + "\"}", agreeContactTempleteCode);
        } catch (ClientException e) {
            e.printStackTrace();
        }
		return null;
    }
    
}