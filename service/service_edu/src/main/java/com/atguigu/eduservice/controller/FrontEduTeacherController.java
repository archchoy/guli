package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.eduservice.service.FrontEduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/edu-service")
@Api(tags = "讲师管理(前台系统)")
public class FrontEduTeacherController {

    @Autowired
    private FrontEduTeacherService frontEduTeacherService;

    @ApiOperation("讲师列表分页")
    @GetMapping("/listFrontPageTeacher/{page}/{limit}")
    public CommonResult listFrontPageTeacher(@PathVariable("page") Integer page,@PathVariable("limit") Integer limit){
        return frontEduTeacherService.listFrontPageTeacher(page,limit);
    }

    @GetMapping("/getTeacherInfoDetails/{teacherId}")
    public CommonResult getTeacherInfoDetails(@PathVariable("teacherId") String teacherId){
        return frontEduTeacherService.getTeacherInfoDetails(teacherId);
    }

}
