package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.result.CommonResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/edu-service/user")
public class EduTeacherLoginController {
    @PostMapping("/login")
    public CommonResult login(){
        return CommonResult.ok().data("token","admin");
    }

    @GetMapping("/info")
    public CommonResult info(){
        return CommonResult.ok().data("roles","[admin]").data("name","admin").data("avatar","https://rofox.oss-cn-guangzhou.aliyuncs.com/common_downloads/images/20220416202019.jpg");
    }

}
