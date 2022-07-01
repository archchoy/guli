package com.atguigu.eduservice.service;

import com.atguigu.commonutils.result.CommonResult;

public interface FrontIndexService {
    CommonResult initIndexTeacher(Integer page,Integer limit);

    CommonResult initIndexCourse(Integer page, Integer limit);
}
