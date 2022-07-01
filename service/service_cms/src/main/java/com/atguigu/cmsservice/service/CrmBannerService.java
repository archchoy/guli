package com.atguigu.cmsservice.service;

import com.atguigu.cmsservice.entity.CrmBanner;
import com.atguigu.cmsservice.entity.CrmBannerVo;
import com.atguigu.commonutils.result.CommonResult;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author RofoX
 * @since 2022-06-01
 */
public interface CrmBannerService extends IService<CrmBanner> {

    CommonResult saveBanner(CrmBannerVo crmBannerVo);

    CommonResult updateBannerById(CrmBannerVo crmBannerVo);

    CommonResult removeBannerById(String id);

    CommonResult bannerPage(Integer page, Integer limit,CrmBannerVo crmBannerVo);

    CommonResult getBannerById(String id);

    CommonResult listBanner();
}
