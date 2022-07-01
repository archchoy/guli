package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.EduTeacherVo;
import com.atguigu.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author RofoX
 * @since 2022-05-09
 */
@Api(tags = "讲师管理(后台系统)")
@CrossOrigin
@RestController
@RequestMapping("/edu-service/teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation("保存讲师")
    @PostMapping
    public CommonResult saveEduTeacher(@RequestBody EduTeacher eduTeacher){
        return eduTeacherService.saveEduTeacher(eduTeacher);
    }

    @ApiOperation("讲师列表")
    @GetMapping("/list")
    public CommonResult listEduTeacher(){
        return eduTeacherService.listEduTeacher();
    }

    @ApiOperation("查询单个讲师")
    @GetMapping("/{id}")
    public CommonResult queryOneEduTeacher(@PathVariable("id") String id){
        return eduTeacherService.queryOneEduTeacher(id);
    }

    @ApiOperation("修改讲师")
    @PutMapping
    public CommonResult updateEduTeacher(@RequestBody EduTeacher eduTeacher){
        return eduTeacherService.updateEduTeacher(eduTeacher);
    }

    @ApiOperation("删除讲师")
    @DeleteMapping("/{id}")
    public CommonResult removeEduTeacher(@PathVariable("id") String id){
        return eduTeacherService.removeEduTeacher(id);
    }

    @ApiOperation("分页查询讲师")
    @PostMapping("/{current}/{limit}")
    public CommonResult pageEduTeacher(
            @PathVariable("current") Integer current,@PathVariable("limit") Integer limit, @RequestBody(required = false) EduTeacherVo eduTeacherVo){
        return eduTeacherService.pageEduTeacher(current,limit,eduTeacherVo);
    }


}

