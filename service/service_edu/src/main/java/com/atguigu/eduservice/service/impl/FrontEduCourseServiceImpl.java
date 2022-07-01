package com.atguigu.eduservice.service.impl;

import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.vo.CourseDetailsVo;
import com.atguigu.eduservice.entity.vo.EduChapterVo;
import com.atguigu.eduservice.entity.vo.FrontCourseVo;
import com.atguigu.eduservice.mapper.EduCourseMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.FrontEduCourseService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FrontEduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements FrontEduCourseService {

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private EduCourseService eduCourseService;

    @Override
    @Cacheable(value = "pageCourse")
    public CommonResult listFrontPageCourse(Integer page, Integer limit, FrontCourseVo frontCourseVo) {
        Page<EduCourse> coursePage = new Page<>(page,limit);
        LambdaQueryWrapper<EduCourse> eduCourseQueryWrapper = new LambdaQueryWrapper<>();
        // 根据浏览量
        if (frontCourseVo.isOrderByBuyCount()) {
            eduCourseQueryWrapper.orderByDesc(EduCourse::getBuyCount);
        }
        // 根据名称
        eduCourseQueryWrapper.like(StringUtils.isNoneEmpty(frontCourseVo.getTitle()),EduCourse::getTitle,frontCourseVo.getTitle());
        // 根据价格
        if (frontCourseVo.isOrderByPriceDesc()) {
            eduCourseQueryWrapper.orderByDesc(EduCourse::getPrice);
        }
        // 根据课程二级分类
        eduCourseQueryWrapper.eq(StringUtils.isNotEmpty(frontCourseVo.getSubjectId()),EduCourse::getSubjectId,frontCourseVo.getSubjectId());

        // 根据课程一级分类
        eduCourseQueryWrapper.eq(StringUtils.isNotEmpty(frontCourseVo.getSubjectParentId()),EduCourse::getSubjectParentId,frontCourseVo.getSubjectParentId());

        if (frontCourseVo.isOrderByTimeDesc()){
            eduCourseQueryWrapper.orderByDesc(EduCourse::getGmtCreate);
        }
        baseMapper.selectPage(coursePage,eduCourseQueryWrapper);
        return CommonResult.ok().data("coursePage",coursePage);
    }

    @Override
    public CommonResult getFrontCourseDetails(String courseId) {
        List<EduChapterVo> eduChapterVos = eduChapterService.listChapterAndVideo(courseId);
        CourseDetailsVo courseDetailsVo = eduCourseService.getCourseDetails(courseId);
        return CommonResult.ok().data("list",eduChapterVos).data("courseDetailsVo",courseDetailsVo);
    }
}
