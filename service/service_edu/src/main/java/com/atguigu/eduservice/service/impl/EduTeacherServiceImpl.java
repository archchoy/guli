package com.atguigu.eduservice.service.impl;

import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.commonutils.result.ResultCode;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.EduTeacherVo;
import com.atguigu.eduservice.mapper.EduTeacherMapper;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author RofoX
 * @since 2022-05-09
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {


    @Override
    @CacheEvict(value = "indexTeacherCache",allEntries = true)
    public CommonResult saveEduTeacher(EduTeacher eduTeacher) {
        if (baseMapper.insert(eduTeacher) > 0 ){
            return CommonResult.ok().data("msg","添加讲师成功").code(ResultCode.SUCCESS);
        }
        return CommonResult.error().data("msg","添加讲师出错了").code(ResultCode.ERROR);
    }

    @Override
    public CommonResult listEduTeacher() {
        List<EduTeacher> eduTeacherList = baseMapper.selectList(null);
        return CommonResult.ok().data("list",eduTeacherList).code(ResultCode.SUCCESS);
    }

    @Override
    public CommonResult queryOneEduTeacher(String id) {
        EduTeacher eduTeacher = baseMapper.selectById(id);
        return CommonResult.ok().data("eduTeacher",eduTeacher);
    }

    @Override
    @CacheEvict(value = "indexTeacherCache",allEntries = true)
    public CommonResult updateEduTeacher(EduTeacher eduTeacher) {
        if (baseMapper.updateById(eduTeacher) > 0){
            return CommonResult.ok().data("updateTeacher",eduTeacher).code(ResultCode.SUCCESS);
        }
       return CommonResult.error().data("msg","修改出错了").code(ResultCode.ERROR);
    }

    @Override
    @CacheEvict(value = "indexTeacherCache",allEntries = true)
    public CommonResult removeEduTeacher(String id) {
        if (baseMapper.deleteById(id)>0){
            return CommonResult.ok().data("msg","删除讲师成功").code(ResultCode.SUCCESS);
        }
        return CommonResult.error().data("msg","删除讲师失败").code(ResultCode.ERROR);
    }


    @Override
    public CommonResult pageEduTeacher(Integer page, Integer limit, EduTeacherVo eduTeacherVo) {
        Page<EduTeacher> pageInfo = new Page<>(page,limit);
        LambdaQueryWrapper<EduTeacher> pageEduTeacherQueryWrapper = new LambdaQueryWrapper<>();
        pageEduTeacherQueryWrapper.orderByDesc(EduTeacher::getGmtCreate);
        // 如果讲师名称不为空则以(讲师名称)作为一个查询条件
        if (eduTeacherVo.getName() != null ) {
            pageEduTeacherQueryWrapper.like(EduTeacher::getName, eduTeacherVo.getName());
        }
        // 讲师资历
        if (eduTeacherVo.getCareer() != null ) {
            pageEduTeacherQueryWrapper.like(EduTeacher::getCareer, eduTeacherVo.getCareer());
        }
        // 如果讲师简介不为空则将(讲师简介)作为一个查询条件
        if (eduTeacherVo.getIntro() != null ) {
            pageEduTeacherQueryWrapper.like(EduTeacher::getIntro, eduTeacherVo.getIntro());
        }
        // 如果讲师名称不为空则以(讲师等级)作为一个查询条件
        if (eduTeacherVo.getLevel() != null ) {
            pageEduTeacherQueryWrapper.eq(EduTeacher::getLevel, eduTeacherVo.getLevel());
        }
        // 如果查询条件中的讲师创建时间不为空则以大于(创建时间)作为一个查询条件
        if (eduTeacherVo.getGmtCreateBegin() != null ) {
            pageEduTeacherQueryWrapper.ge(EduTeacher::getGmtCreate, eduTeacherVo.getGmtCreateBegin());
        }
        // 如果查询条件中讲师的创建时间不为空则以小于(创建时间)作为一个查询条件
        if (eduTeacherVo.getGmtCreateEnd() != null ) {
            pageEduTeacherQueryWrapper.le(EduTeacher::getGmtCreate, eduTeacherVo.getGmtCreateEnd());
        }
        baseMapper.selectPage(pageInfo,pageEduTeacherQueryWrapper);
        return CommonResult.ok().data("page",pageInfo);
    }
}
