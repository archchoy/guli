package com.atguigu.servicemsm.controller;

import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.servicemsm.service.MSMService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/msm-service/message")
@Api(tags = "短信服务管理")
public class MSMController {

    @Autowired
    private MSMService msmService;

    @ApiOperation("发送用户注册信息")
    @GetMapping("/send/{mobile}")
    public CommonResult sendSignInCode(@PathVariable("mobile") String mobile) {
        return msmService.sendSignInCode(mobile);
    }
}

