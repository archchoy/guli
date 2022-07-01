package com.atguigu.serviceoss.controller;

import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.serviceoss.service.OSSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@Api(tags = "云存储服务管理")
@RequestMapping("/oss-service/file")
public class OSSController {

    @Autowired
    private OSSService ossService;

    @ApiOperation("流式文件上传接口")
    @PostMapping
    public CommonResult uploadAvatar(MultipartFile file){
        return ossService.uploadAvatar(file);
    }
}

