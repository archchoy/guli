package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduCourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author RofoX
 * @since 2022-05-14
 */
public interface EduCourseDescriptionService extends IService<EduCourseDescription> {

    // 保存描述信息
    int saveDescription(EduCourseDescription description);

    EduCourseDescription getDescription(String courseId);
}
