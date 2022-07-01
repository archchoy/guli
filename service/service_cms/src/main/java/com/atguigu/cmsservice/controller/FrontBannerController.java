package com.atguigu.cmsservice.controller;

import com.atguigu.cmsservice.service.CrmBannerService;
import com.atguigu.commonutils.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@Api(tags = "网站横幅(前台系统)")
@RequestMapping("/cms-service/banner")
public class FrontBannerController {

    @Autowired
    private CrmBannerService crmBannerService;

    @ApiOperation("Banner列表")
    @GetMapping("/initBanner")
    public CommonResult listBanner(){
        return crmBannerService.listBanner();
    }
}
