package com.atguigu.serviceoss.common;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component  // 交给Spring管理
public class OSSAccessInfo implements InitializingBean {

    // 注入属性
    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;
    @Value("${aliyun.oss.file.accesskeyid}")
    private String accessKeyId;
    @Value("${aliyun.oss.file.accesskeysecret}")
    private String accessKeySecret;
    @Value("${aliyun.oss.file.endpoint}")
    private String endPoint;


    public static String BUCKET_NAME;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String END_POINT;

    @Override
    public void afterPropertiesSet() throws Exception {
        BUCKET_NAME = bucketName;
        ACCESS_KEY_ID = accessKeyId;
        ACCESS_KEY_SECRET = accessKeySecret;
        END_POINT = endPoint;
    }
}
