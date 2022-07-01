package com.atguigu.client;

import com.atguigu.client.Impl.VodFileDegradeFeignClient;
import com.atguigu.commonutils.result.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Component
@FeignClient(value = "service-vod",fallback = VodFileDegradeFeignClient.class)
public interface VodClient {
    // 视频点播服务流式上传音视频文件 Feign 客户端
    @PostMapping("/video-service/video/uploadByStream")
    public CommonResult uploadByStream(MultipartFile file);

    // 视频点播服务删除音视频文件 Feign 客户端

    @DeleteMapping("/video-service/video")
    public CommonResult removeVideoByIds(@RequestParam("videoIds") String videoIds);
}
