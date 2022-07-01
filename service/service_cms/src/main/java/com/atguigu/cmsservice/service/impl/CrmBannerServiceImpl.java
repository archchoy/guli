package com.atguigu.cmsservice.service.impl;

import com.atguigu.cmsservice.entity.CrmBanner;
import com.atguigu.cmsservice.entity.CrmBannerVo;
import com.atguigu.cmsservice.mapper.CrmBannerMapper;
import com.atguigu.cmsservice.service.CrmBannerService;
import com.atguigu.commonutils.result.CommonResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author RofoX
 * @since 2022-06-01
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    public CommonResult saveBanner(CrmBannerVo crmBannerVo) {
        LambdaQueryWrapper<CrmBanner> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CrmBanner::getTitle,crmBannerVo.getTitle());
        if (baseMapper.selectCount(queryWrapper)>0){
            return CommonResult.error().message("标题已存在");
        }
        CrmBanner banner = new CrmBanner();
        BeanUtils.copyProperties(crmBannerVo,banner);
        baseMapper.insert(banner);
        return CommonResult.ok().message("添加成功");
    }

    @Override
    public CommonResult updateBannerById(CrmBannerVo crmBannerVo) {
        CrmBanner banner = new CrmBanner();
        BeanUtils.copyProperties(crmBannerVo,banner);
        baseMapper.updateById(banner);
        return CommonResult.ok().message("修改成功");
    }

    @Override
    public CommonResult removeBannerById(String id) {
        baseMapper.deleteById(id);
        return CommonResult.ok().message("删除成功");
    }

    @Override
    public CommonResult bannerPage(Integer page, Integer limit,CrmBannerVo crmBannerVo) {
        Page<CrmBanner> pageInfo = new Page<>(page,limit);
        LambdaQueryWrapper<CrmBanner> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(crmBannerVo.getTitle() != null,CrmBanner::getTitle,crmBannerVo.getTitle());
        queryWrapper.eq(crmBannerVo.getSort() != null,CrmBanner::getSort,crmBannerVo.getSort());
        queryWrapper.orderByDesc(CrmBanner::getSort).orderByDesc(CrmBanner::getGmtCreate);
        baseMapper.selectPage(pageInfo,queryWrapper);
        return CommonResult.ok().data("pageInfo",pageInfo);
    }

    @Override
    public CommonResult getBannerById(String id) {
        CrmBanner banner = baseMapper.selectById(id);
        return CommonResult.ok().data("banner",banner);
    }

    @Override
    public CommonResult listBanner() {
        LambdaQueryWrapper<CrmBanner> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(CrmBanner::getSort).orderByDesc(CrmBanner::getGmtCreate);
        List<CrmBanner> bannerList = baseMapper.selectList(queryWrapper);
        return CommonResult.ok().data("bannerList",bannerList);
    }
}
