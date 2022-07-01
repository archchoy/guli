package com.atguigu.eduservice.service;

import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.vo.FrontCourseVo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface FrontEduCourseService extends IService<EduCourse> {
    /**
     * 前台课程列表展示
     * @param page 页码
     * @param limit 每页数量
     * @param frontCourseVo 检索条件
     * @return 课程蒋所条件
     */
    CommonResult listFrontPageCourse(Integer page, Integer limit, FrontCourseVo frontCourseVo);

    /**
     * 根据课程Id显示前端课程详情
     * @param courseId 课程Id
     * @return 课程详情
     */
    CommonResult getFrontCourseDetails(String courseId);
}
