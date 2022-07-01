package com.atguigu.client.Impl;

import com.atguigu.client.VodClient;
import com.atguigu.commonutils.result.CommonResult;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class VodFileDegradeFeignClient implements VodClient {
    @Override
    public CommonResult uploadByStream(MultipartFile file) {
        return CommonResult.error().message("上传出错了");
    }

    @Override
    public CommonResult removeVideoByIds(String videoIds) {
        return CommonResult.error().message("删除视频出错了");
    }
}
