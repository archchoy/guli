package com.atguigu.servicemsm.service;

import com.atguigu.commonutils.result.CommonResult;

public interface MSMService {
    CommonResult sendSignInCode(String phone);
}
