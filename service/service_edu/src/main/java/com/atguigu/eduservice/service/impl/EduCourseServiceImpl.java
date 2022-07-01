package com.atguigu.eduservice.service.impl;

import com.atguigu.client.VodClient;
import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.eduservice.entity.*;
import com.atguigu.eduservice.entity.vo.CourseDetailsVo;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.mapper.EduCourseMapper;
import com.atguigu.eduservice.service.*;
import com.atguigu.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author RofoX
 * @since 2022-05-14
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private EduSubjectService eduSubjectService;
    @Autowired
    private EduTeacherService eduTeacherService;

    @Autowired
    private EduChapterService eduChapterService;
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    private VodClient vodClient;

    /**
     * @Description 保存课程相关信息
     * @param courseInfoVo 课程信息
     * @return 课程ID
     */
    @Override
    @Transactional
//    @CacheEvict(value = "indexCourseCache",allEntries = true)
    public CommonResult saveEduCourse(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert_course = baseMapper.insert(eduCourse);
        EduCourseDescription description = new EduCourseDescription();
        description.setDescription(courseInfoVo.getDescription());
        // EduCourse的Id设置为EduCourseDescription
        description.setId(eduCourse.getId());
        int insert_description = eduCourseDescriptionService.saveDescription(description);
        if (insert_course > 0 && insert_description > 0) {
            return CommonResult.ok().data("courseId", eduCourse.getId()).message("课程信息保存成功");
        }else {
            throw new GuliException(20001,"课程信息添加失败");
        }
    }


    /**
     * @Description 根据Id获取课程相关信息(基本课程信息和课程描述信息)
     * @param courseId 课程Id
     * @return 课程相关信息
     */
    @Override
    public CommonResult getCourseInfoById(String courseId) {
        EduCourse course = this.getById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(course,courseInfoVo);
        EduCourseDescription description = eduCourseDescriptionService.getDescription(courseId);
            courseInfoVo.setDescription(description.getDescription());
        return CommonResult.ok().data("courseInfo",courseInfoVo);
    }

    /**
     * @Description 修改课程相关信息
     * @param courseInfoVo 课程信息
     * @return
     */
    @Override
    @Transactional
//    @CacheEvict(value = "indexCourseCache",allEntries = true)
    public CommonResult updateCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,course);
        this.updateById(course);
        EduCourseDescription description = new EduCourseDescription();
            description.setDescription(courseInfoVo.getDescription());
            description.setId(courseInfoVo.getId());
            eduCourseDescriptionService.updateById(description);
        return CommonResult.ok().message("操作成功");
    }

    /**
     * @Description 根据条件分页查询课程相关信息
     * @param page 当前页码
     * @param limit 查询数量大小
     * @param courseInfoVo 分页条件
     * @return
     */
    @Override
    public CommonResult listPageCourseInfo(Integer page,Integer limit,CourseInfoVo courseInfoVo) {
        LambdaQueryWrapper<EduCourse> courseQueryWrapper = new LambdaQueryWrapper<>();
        courseQueryWrapper.like(courseInfoVo.getTitle() !=null,EduCourse::getTitle,courseInfoVo.getTitle());
        courseQueryWrapper.eq(courseInfoVo.getStatus() != null,EduCourse::getStatus,courseInfoVo.getStatus());
        courseQueryWrapper.orderByDesc(EduCourse::getGmtCreate);
        Page<EduCourse> pageInfo = new Page<>(page,limit);
        baseMapper.selectPage(pageInfo, courseQueryWrapper);
        return CommonResult.ok().data("page",pageInfo);
    }

    /**
     * @Description 获取发布课程相关信息
     * @param courseId 课程ID
     * @return
     */
    @Override
    public CommonResult getPublishCourseInfo(String courseId) {
        // 课程基本信息
        EduCourse course = baseMapper.selectById(courseId);
        CoursePublishVo coursePublishVo = new CoursePublishVo();
        BeanUtils.copyProperties(course,coursePublishVo);
        // 设置讲师信息
        EduTeacher teacher = eduTeacherService.getById(course.getTeacherId());
        coursePublishVo.setTeacherName(teacher.getName());
        // 设置课程一级分类信息
        EduSubject subjectOne = eduSubjectService.getById(course.getSubjectParentId());
        coursePublishVo.setPrimarySubjectName(subjectOne.getTitle());
        // 设置课程二级分类信息
        EduSubject subjectTwo = eduSubjectService.getById(course.getSubjectId());
        coursePublishVo.setSecondarySubjectName(subjectTwo.getTitle());
        return CommonResult.ok().data("coursePublishVo",coursePublishVo);
    }

    /**
     * @Description 删除课程相关信息
     * @param courseId 课程ID
     * @return
     */
    @Override
    @Transactional
//    @CacheEvict(value = "indexCourseCache",allEntries = true)
    public CommonResult removeCourse(String courseId) {
        LambdaQueryWrapper<EduVideo> videoQueryWrapper = new LambdaQueryWrapper<>();
        videoQueryWrapper.eq(EduVideo::getCourseId,courseId);
        int VIDEO_COUNT = eduVideoService.count(videoQueryWrapper);
        List<EduVideo> eduVideoList = eduVideoService.list(videoQueryWrapper);
        List<String> videoSourceIdList = eduVideoList.stream().map(EduVideo::getVideoSourceId).collect(Collectors.toList());
        if (videoSourceIdList.size()>0) {
            vodClient.removeVideoByIds(StringUtils.join(videoSourceIdList, ","));
        }
        // 删除视频信息
        if (VIDEO_COUNT > 0) {
            eduVideoService.remove(videoQueryWrapper);
        }
        // 删除章节信息
        LambdaQueryWrapper<EduChapter> chapterQueryWrapper = new LambdaQueryWrapper<>();
        chapterQueryWrapper.eq(EduChapter::getCourseId,courseId);
        int CHAPTER_COUNT = eduChapterService.count(chapterQueryWrapper);
        if (CHAPTER_COUNT > 0) {
            eduChapterService.remove(chapterQueryWrapper);
        }
        // 删除描述信息
        LambdaQueryWrapper<EduCourseDescription> descriptionQueryWrapper = new LambdaQueryWrapper<>();
        descriptionQueryWrapper.eq(EduCourseDescription::getId,courseId);
        int DESCRIPTION_COUNT = eduCourseDescriptionService.count(descriptionQueryWrapper);
        if (DESCRIPTION_COUNT > 0 ){
            eduCourseDescriptionService.remove(descriptionQueryWrapper);
        }
        baseMapper.deleteById(courseId);
        return CommonResult.ok().message("删除课程信息成功");
    }


    @Override
    public CourseDetailsVo getCourseDetails(String courseId) {
        return baseMapper.getFrontCourseDetails(courseId);
    }
}
