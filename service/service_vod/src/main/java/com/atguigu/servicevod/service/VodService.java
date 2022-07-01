package com.atguigu.servicevod.service;

import com.atguigu.commonutils.result.CommonResult;
import org.springframework.web.multipart.MultipartFile;

public interface VodService {

    CommonResult uploadByStream(MultipartFile file);

    CommonResult removeVideo(String videoId);

    CommonResult getPlayAuth(String id);
}
