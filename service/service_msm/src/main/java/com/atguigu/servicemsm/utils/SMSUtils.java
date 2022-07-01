package com.atguigu.servicemsm.utils;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.teaopenapi.models.Config;
import com.atguigu.servicemsm.entity.SMSAccessInfo;

/**
 * 发送验证码工具类
 */
public class SMSUtils {
    // 发送验证码
    public static boolean sendCheckCode(String phone,String code){
        try {
            Config config = new Config();
            // 设置短信服务的AccessKeyId
            config.setAccessKeyId(SMSAccessInfo.ACCESS_KEY_ID);
            // 设置肚腩新服务的AccessKeySecret
            config.setAccessKeySecret(SMSAccessInfo.ACCESS_KEY_SECRET);
            // 设置短信服务的endPoint
            config.setEndpoint(SMSAccessInfo.END_POINT);
            Client client = new Client(config);
            SendSmsRequest request = new SendSmsRequest();
            // 设置接收短信的手机号
            request.setPhoneNumbers(phone)
                    // 设置短信服务签名
//                    .setSignName(SMSAccessInfo.SIGN_NAME)
                    .setSignName("阿里云短信测试")
                    // 设置短信服务模板编号
                    .setTemplateCode(SMSAccessInfo.TEMPLATE_CODE)
                    // 设置短信验证码
                    .setTemplateParam("{\"code\":\"" + code + "\"}");
            // 发送短信
            client.sendSms(request);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
