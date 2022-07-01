package com.atguigu.ucenterservice.service;

import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.ucenterservice.entity.MemberRegVo;
import com.atguigu.ucenterservice.entity.UcenterMember;
import com.atguigu.ucenterservice.entity.MemberLoginVo;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author RofoX
 * @since 2022-06-07
 */
public interface UcenterMemberService extends IService<UcenterMember> {
    /**
     * 用户登录接口
     * @param memberLoginVo 用户登录信息
     * @return JWT-token
     */
    CommonResult userLogin(MemberLoginVo memberLoginVo);

    CommonResult registerMember(MemberRegVo memberRegVo);

    CommonResult getMemberInfo(HttpServletRequest request);
}
