package com.atguigu.servicevod.controller;


import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.servicevod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@Api(tags = "视频点播服务管理")
@RequestMapping("/video-service/video")
public class VodController {

    @Autowired
    private VodService vodService;

    @ApiOperation("流式上传音频视频")
    @PostMapping("/uploadByStream")
    public CommonResult uploadByStream(MultipartFile file){
        return vodService.uploadByStream(file);
    }

    @ApiOperation("根据视频Id删除音视频")
    @DeleteMapping
    public CommonResult removeVideo(String videoIds){
        return vodService.removeVideo(videoIds);
    }

    @GetMapping("getPlayAuth/{id}")
    public CommonResult getPlayAuth(@PathVariable("id") String id){
        return vodService.getPlayAuth(id);

    }


}
