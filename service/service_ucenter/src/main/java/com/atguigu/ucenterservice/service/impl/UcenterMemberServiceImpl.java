package com.atguigu.ucenterservice.service.impl;

import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.commonutils.utils.JwtUtils;
import com.atguigu.ucenterservice.entity.MemberRegVo;
import com.atguigu.ucenterservice.entity.UcenterMember;
import com.atguigu.ucenterservice.entity.MemberLoginVo;
import com.atguigu.ucenterservice.mapper.UcenterMemberMapper;
import com.atguigu.ucenterservice.service.UcenterMemberService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author RofoX
 * @since 2022-06-07
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {


    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public CommonResult getMemberInfo(HttpServletRequest request) {
        // 根据Token获取Id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        // 查询用户信息
        UcenterMember member = baseMapper.selectById(memberId);
        if (member == null ){
            return CommonResult.error().message("登录信息异常");
        }
        return CommonResult.ok().data("member",member).message("登录成功");
    }

    @Override
    public CommonResult registerMember(MemberRegVo memberRegVo) {
        // 判断验证码是否正确
        String regCode = redisTemplate.opsForValue().get("reg-" + memberRegVo.getMobile());
        if (!memberRegVo.getCode().equals(regCode)){
            return CommonResult.error().message("验证码错误");
        }
        // 判断用户是否存在
        LambdaQueryWrapper<UcenterMember> memberQueryWrapper = new LambdaQueryWrapper<>();
        memberQueryWrapper.eq(UcenterMember::getMobile,memberRegVo.getMobile());
        if (baseMapper.selectCount(memberQueryWrapper) > 0){
            return CommonResult.error().message("用户已存在");
        }
        // 注册用户
        UcenterMember member = new UcenterMember();
        member.setMobile(memberRegVo.getMobile());
        member.setNickname(memberRegVo.getNickname());
        member.setPassword(DigestUtils.md5DigestAsHex(memberRegVo.getPassword().getBytes()));
        baseMapper.insert(member);
        return CommonResult.ok().message("注册成功");
    }

    @Override
    public CommonResult userLogin(MemberLoginVo memberLoginVo) {
        LambdaQueryWrapper<UcenterMember> loginQueryWrapper = new LambdaQueryWrapper<>();
        loginQueryWrapper.eq(UcenterMember::getMobile, memberLoginVo.getMobile());
        UcenterMember member = baseMapper.selectOne(loginQueryWrapper);
        if (member == null){
            return CommonResult.error().message("用户不存在");
        }
        if (!member.getPassword().equals(DigestUtils.md5DigestAsHex(memberLoginVo.getPassword().getBytes()))){
            return CommonResult.error().message("您输入的密码不正确,请重试");
        }
        if (member.getIsDisabled()){    // 账号是否禁用
            return CommonResult.error().message("该账号已被禁用");
        }
        // 以上条件都不满足则登陆成功 返回token
        String token = JwtUtils.getJwtToken(member.getId(), member.getNickname());
        return CommonResult.ok().data("token",token);
    }
}
