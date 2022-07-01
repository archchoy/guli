package com.atguigu.eduservice.service.impl;

import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.mapper.EduTeacherMapper;
import com.atguigu.eduservice.service.FrontEduCourseService;
import com.atguigu.eduservice.service.FrontEduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrontEduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements FrontEduTeacherService {

    @Autowired
    private FrontEduCourseService frontEduCourseService;

    @Override
    @Cacheable(value = "pageTeacher")
    public CommonResult listFrontPageTeacher(Integer page,Integer limit) {
        Page<EduTeacher> teacherPage = new Page<>(page,limit);
        LambdaQueryWrapper<EduTeacher> teacherQueryWrapper = new LambdaQueryWrapper<>();
        teacherQueryWrapper.orderByDesc(EduTeacher::getSort);
        baseMapper.selectPage(teacherPage,teacherQueryWrapper);
        return CommonResult.ok().data("teacherPage",teacherPage);
    }

    @Override
    public CommonResult getTeacherInfoDetails(String teacherId) {
        List<EduCourse> courseList = null;
        // 查询讲师信息
        EduTeacher teacher = baseMapper.selectById(teacherId);
        // 查询所讲课程信息
        LambdaQueryWrapper<EduCourse> courseWrapper = new LambdaQueryWrapper<>();
        if (frontEduCourseService.count(courseWrapper)>0) {
            courseWrapper.eq(EduCourse::getTeacherId, teacherId);
            courseList = frontEduCourseService.list(courseWrapper);
        }
        return CommonResult.ok().data("teacher", teacher).data("courseList", courseList);
    }
}
