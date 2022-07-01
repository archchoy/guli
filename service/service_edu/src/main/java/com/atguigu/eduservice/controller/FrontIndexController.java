package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.eduservice.service.FrontIndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@Api(tags = "初始化首页数据(前台系统)")
@RequestMapping("/edu-service/index")
public class FrontIndexController {

    @Autowired
    private FrontIndexService frontIndexService;

    @ApiOperation("初始化首页讲师数据")
    @GetMapping("/teacher/{page}/{limit}")
    public CommonResult initIndexTeacher(@PathVariable("page") Integer page,@PathVariable("limit") Integer limit){
        return frontIndexService.initIndexTeacher(page,limit);
    }

    @ApiOperation("初始化首页课程数据")
    @GetMapping("/course/{page}/{limit}")
    public CommonResult initIndexCourse(@PathVariable("page") Integer page,@PathVariable("limit") Integer limit){
        return frontIndexService.initIndexCourse(page,limit);
    }

}
