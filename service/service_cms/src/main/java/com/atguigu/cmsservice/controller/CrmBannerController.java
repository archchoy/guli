package com.atguigu.cmsservice.controller;


import com.atguigu.cmsservice.entity.CrmBannerVo;
import com.atguigu.cmsservice.service.CrmBannerService;
import com.atguigu.commonutils.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author RofoX
 * @since 2022-06-01
 */
@Api(tags = "网站横幅管理(后台系统)")
@CrossOrigin
@RestController
@RequestMapping("/cms-service/banner")
public class CrmBannerController {
    @Autowired
    private CrmBannerService crmBannerService;

    @ApiOperation("添加Banner")
    @PostMapping
    public CommonResult saveBanner(@RequestBody CrmBannerVo crmBannerVo){
        return crmBannerService.saveBanner(crmBannerVo);
    }

    @ApiOperation("修改Banner")
    @PutMapping
    public CommonResult updateBannerById(@RequestBody CrmBannerVo crmBannerVo){
        return crmBannerService.updateBannerById(crmBannerVo);
    }

    @ApiOperation("删除Banner")
    @DeleteMapping("/{id}")
    public CommonResult removeBannerById(@PathVariable("id") String id){
        return crmBannerService.removeBannerById(id);
    }

    @ApiOperation("条件分页查询Banner")
    @PostMapping("/{page}/{limit}")
    public CommonResult listBannerPage(
            @PathVariable("page") Integer page
            ,@PathVariable("limit") Integer limit
            ,@RequestBody(required = false) CrmBannerVo crmBannerVo){
        return crmBannerService.bannerPage(page,limit,crmBannerVo);
    }

    @ApiOperation("根据Id获取Banner")
    @GetMapping("/{id}")
    public CommonResult getBannerById(@PathVariable("id") String id){
        return crmBannerService.getBannerById(id);
    }

}

