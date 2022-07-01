package com.atguigu.servicemsm.service.Impl;

import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.servicemsm.service.MSMService;
import com.atguigu.servicemsm.utils.CheckCodeGenerator;
import com.atguigu.servicemsm.utils.SMSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
@Slf4j
@Service
public class MSMServiceImpl implements MSMService {

    @Resource
    private RedisTemplate<String,String> redisTemplate;
    
    @Override
    public CommonResult sendSignInCode(String phone) {
        String code = String.valueOf(CheckCodeGenerator.generateValidateCode(4));
        log.info("测试验证码 : " + code);
//        if (SMSUtils.sendCheckCode(phone,code)){
            // 将验证码存入Redis 并且设置过期时间
            redisTemplate.opsForValue().set("reg-" + phone , code, 5,TimeUnit.MINUTES);
            return CommonResult.ok().message("验证码发送成功,5分钟内有效");
//        }
//        return CommonResult.error().message("验证码发送异常");
    }
}
