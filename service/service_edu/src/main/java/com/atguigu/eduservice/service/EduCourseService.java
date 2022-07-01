package com.atguigu.eduservice.service;

import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.vo.CourseDetailsVo;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author RofoX
 * @since 2022-05-14
 */
public interface EduCourseService extends IService<EduCourse> {

    /**
     * 保存课程信息
     * @param courseInfoVo 课程信息
     */
    CommonResult saveEduCourse(CourseInfoVo courseInfoVo);

    /**
     * 根据歌词ID获取课程信息
     * @param courseId 课程Id
     */
    CommonResult getCourseInfoById(String courseId);

    /**
     *  修改课程信息
     * @param courseInfoVo 课程信息
     */
    CommonResult updateCourseInfo(CourseInfoVo courseInfoVo);

    /**
     * 分页条件查询课程信息
     * @param page 当前页码
     * @param limit 查询数量大小
     * @param courseInfoVo 分页条件
     * @return 分页条件对象
     */
    CommonResult listPageCourseInfo(Integer page,Integer limit,CourseInfoVo courseInfoVo);

    /**
     * 获取发布信息
     * @param courseId 课程ID
     */
    CommonResult getPublishCourseInfo(String courseId);

    /**
     * 删除课程信息, 包含章节, 小节, 描述, 课程信息
     * @param courseId 课程ID
     */
    CommonResult removeCourse(String courseId);

    CourseDetailsVo getCourseDetails(String courseId);
}
