package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.eduservice.entity.vo.EduChapterVo;
import com.atguigu.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author RofoX
 * @since 2022-05-14
 */
@Api(tags = "课程章节管理(后台系统)")
@RestController
@CrossOrigin
@RequestMapping("/edu-service/chapter")
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    @ApiOperation("获取所有章节和小节列表")
    @GetMapping("list/{courseId}")
    public CommonResult listChapterAndVideo(@PathVariable("courseId") String courseId){
        List<EduChapterVo> eduChapterVos = eduChapterService.listChapterAndVideo(courseId);
        return CommonResult.ok().data("list",eduChapterVos);
    }


    @ApiOperation("添加章节信息")
    @PostMapping
    public CommonResult saveEduChapterVideo(@RequestBody EduChapterVo eduChapterVo){
        return eduChapterService.saveEduChapter(eduChapterVo);
    }

    @ApiOperation("修改章节信息")
    @PutMapping
    public CommonResult updateChapterVideo(@RequestBody EduChapterVo eduChapterVo){
        return eduChapterService.updateChapter(eduChapterVo);
    }

    @ApiOperation("删除章节信息")
    @DeleteMapping("/{id}")
    public CommonResult removeChapter(@PathVariable("id") String id){
        return eduChapterService.removeChapter(id);
    }


    @ApiOperation("根据章节Id获取章节信息")
    @GetMapping("/{id}")
    public CommonResult getChapterById(@PathVariable("id") String id){
        return eduChapterService.getChapterById(id);
    }



}

