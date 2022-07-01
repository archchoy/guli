package com.atguigu.serviceoss.service;

import com.atguigu.commonutils.result.CommonResult;
import org.springframework.web.multipart.MultipartFile;

public interface OSSService {
    CommonResult uploadAvatar(MultipartFile file);
}
