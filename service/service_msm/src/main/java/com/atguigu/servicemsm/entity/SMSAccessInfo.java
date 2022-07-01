package com.atguigu.servicemsm.entity;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SMSAccessInfo implements InitializingBean {

    @Value("${sms.config.accesskeyid}")
    private String accessKeyId;

    @Value("${sms.config.accesskeysecret}")
    private String accessKeySecret;

    @Value("${sms.config.endpoint}")
    private String endpoint;

    @Value("${sms.config.signname}")
    private String signName;

    @Value("${sms.config.templatecode}")
    private String templateCode;

    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String END_POINT;
    public static String SIGN_NAME;
    public static String TEMPLATE_CODE;


    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = accessKeyId;
        ACCESS_KEY_SECRET = accessKeySecret;
        END_POINT = endpoint;
        SIGN_NAME = signName;
        TEMPLATE_CODE = templateCode;
    }
}
