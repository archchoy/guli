package com.atguigu.eduservice.service.impl;

import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduTeacherService;
import com.atguigu.eduservice.service.FrontIndexService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class FrontIndexServiceImpl implements FrontIndexService {

    @Autowired
    private EduTeacherService eduTeacherService;
    @Autowired
    private EduCourseService eduCourseService;

    @Override
//    @Cacheable( value = "indexCache",key = "")
    public CommonResult initIndexTeacher(Integer page,Integer limit) {
        // 查询首页讲师数据
        LambdaQueryWrapper<EduTeacher> teacherQueryWrapper = new LambdaQueryWrapper<>();
        teacherQueryWrapper.orderByDesc(EduTeacher::getLevel).orderByDesc(EduTeacher::getSort).orderByDesc(EduTeacher::getGmtCreate);
        Page<EduTeacher> pageIngo = new Page<>(page, limit);
        eduTeacherService.page(pageIngo,teacherQueryWrapper);
        // 返回数据
        return CommonResult.ok().data("pageTeacher",pageIngo);
    }

    @Override
//    @Cacheable( value = "indexCache")
    public CommonResult initIndexCourse(Integer page, Integer limit) {
        LambdaQueryWrapper<EduCourse> courseQueryWrapper = new LambdaQueryWrapper<>();
        courseQueryWrapper.orderByDesc(EduCourse::getViewCount).orderByDesc(EduCourse::getBuyCount).orderByDesc(EduCourse::getGmtCreate);
        Page<EduCourse> pageInfo = new Page<>(page, limit);
        eduCourseService.page(pageInfo,courseQueryWrapper);
        return CommonResult.ok().data("pageCourse",pageInfo);
    }
}
