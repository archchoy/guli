package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author RofoX
 * @since 2022-05-14
 */
@CrossOrigin
@RestController
@RequestMapping("/edu-service/course")
@Api(tags = "课程管理(后台系统)")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;


    @ApiOperation("添加课程信息")
    @PostMapping
    public CommonResult saveEduCourse(@RequestBody CourseInfoVo courseInfoVo){
        return eduCourseService.saveEduCourse(courseInfoVo);
    }


    @ApiOperation("根据Id查询课程信息")
    @GetMapping("/{courseId}")
    public CommonResult getCourseInfoById( @PathVariable("courseId") String courseId){
        return eduCourseService.getCourseInfoById(courseId);
    }

    @ApiOperation("修改课程信息")
    @PutMapping
    public CommonResult updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        return eduCourseService.updateCourseInfo(courseInfoVo);
    }

    @ApiOperation("分页查询课程信息")
    @PostMapping("list/{page}/{limit}")
    public CommonResult listPageCourseInfo(
            @PathVariable("page") Integer page,@PathVariable("limit") Integer limit,
            @RequestBody(required = false) CourseInfoVo courseInfoVo){
        return eduCourseService.listPageCourseInfo(page,limit,courseInfoVo);
    }

    @ApiOperation("获取发布信息")
    @GetMapping("publishInfo/{courseId}")
    public CommonResult getPublishCourseInfo(@PathVariable("courseId") String courseId){
        return eduCourseService.getPublishCourseInfo(courseId);
    }

    @ApiOperation("课程信息")
    @DeleteMapping("/{courseId}")
    public CommonResult removeCourseInfo(@PathVariable("courseId") String courseId){
        return eduCourseService.removeCourse(courseId);
    }




}

