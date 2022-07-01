package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.vo.CourseDetailsVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author RofoX
 * @since 2022-05-14
 */

@Mapper
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CourseDetailsVo getFrontCourseDetails(String courseId);
}
