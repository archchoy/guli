package com.atguigu.eduservice.service;

import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

public interface FrontEduTeacherService extends IService<EduTeacher> {
    CommonResult listFrontPageTeacher(Integer page, Integer limit);

    CommonResult getTeacherInfoDetails(String teacherId);
}
