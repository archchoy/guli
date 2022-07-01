package com.atguigu.ucenterservice.controller;


import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.ucenterservice.entity.MemberLoginVo;
import com.atguigu.ucenterservice.entity.MemberRegVo;
import com.atguigu.ucenterservice.service.UcenterMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author RofoX
 * @since 2022-06-07
 */
@CrossOrigin
@RestController
@RequestMapping("/ucenter-service/member")
@Api(tags = "用户成员管理(前台系统)")
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService uCenterMemberService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public CommonResult userLogin(@RequestBody @Validated MemberLoginVo memberLoginVo){
        return uCenterMemberService.userLogin(memberLoginVo);
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public CommonResult register(@RequestBody @Validated MemberRegVo memberRegVo){
        return uCenterMemberService.registerMember(memberRegVo);
    }

    @ApiOperation("根据Token获取用户信息")
    @GetMapping("/memberInfo")
    public CommonResult getMemberInfo(HttpServletRequest request){
        return uCenterMemberService.getMemberInfo(request);
    }


}

