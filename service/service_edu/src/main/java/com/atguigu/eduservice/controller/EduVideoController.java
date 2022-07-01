package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.eduservice.entity.vo.EduVideoVo;
import com.atguigu.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author RofoX
 * @since 2022-05-18
 */
@Api(tags = "课程小节管理(后台系统)")
@CrossOrigin
@RestController
@RequestMapping("/edu-service/video")
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    @ApiOperation("添加小节信息")
    @PostMapping
    public CommonResult saveVideo(@RequestBody EduVideoVo eduVideoVo){
        return eduVideoService.saveVideo(eduVideoVo);
    }

    @ApiOperation("删除小节信息")
    @DeleteMapping("/{id}")
    public CommonResult removeVideo(@PathVariable("id") String id){
        return eduVideoService.removeVideo(id);
    }

    @ApiOperation("修改课程小节信息")
    @PutMapping
    public CommonResult updateVideo(@RequestBody EduVideoVo eduVideoVo){
        return eduVideoService.updateVideo(eduVideoVo);
    }

    @ApiOperation("根据Id查询课程小节信息")
    @GetMapping("/{id}")
    public CommonResult getVideoById(@PathVariable("id") String id){
        return eduVideoService.getVideoById(id);
    }



}

