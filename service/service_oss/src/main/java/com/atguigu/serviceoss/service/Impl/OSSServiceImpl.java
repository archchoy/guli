package com.atguigu.serviceoss.service.Impl;

import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.serviceoss.common.OSSUploadFileUtil;
import com.atguigu.serviceoss.service.OSSService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OSSServiceImpl implements OSSService {
    @Override
    public CommonResult uploadAvatar(MultipartFile file) {
        return CommonResult.ok().data("url", OSSUploadFileUtil.upload(file));
    }
}
