package com.atguigu.servicevod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccessInfo implements InitializingBean {
    @Value("${aliyun.vod.accesskey}")
    private String accessKey;
    @Value("${aliyun.vod.accesskeysecret}")
    private String accessSecret;

    public static String ACCESS_KEY;
    public static String ACCESS_SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY = accessKey;
        ACCESS_SECRET = accessSecret;
    }
}
