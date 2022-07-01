package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.eduservice.entity.vo.FrontCourseVo;
import com.atguigu.eduservice.service.FrontEduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/edu-service")
@Api(tags = "课程管理(前台系统)")
public class FrontEduCourseController {
    @Autowired
    private FrontEduCourseService frontEduCourseService;


    @ApiOperation("课程列表分页")
    @PostMapping("/listFrontPageCourse/{page}/{limit}")
    public CommonResult listFrontPageCourse(
            @PathVariable("page") Integer page
            , @PathVariable("limit") Integer limit
            , @RequestBody(required = false) FrontCourseVo frontCourseVo){
        return frontEduCourseService.listFrontPageCourse(page,limit,frontCourseVo);
    }

    @GetMapping("/getFrontCourseDetails/{courseId}")
    public CommonResult getFrontCourseDetails(@PathVariable("courseId") String courseId){
        return frontEduCourseService.getFrontCourseDetails(courseId);
    }
}
