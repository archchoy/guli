package com.atguigu.eduservice.service.impl;

import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.SubjectParent;
import com.atguigu.eduservice.entity.Subject;
import com.atguigu.eduservice.mapper.EduSubjectMapper;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author RofoX
 * @since 2022-05-13
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {


    @Override
    public EduSubject existsPrimaryEduSubject(String primaryClassificationName) {
        LambdaQueryWrapper<EduSubject> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EduSubject::getParentId, "0").eq(EduSubject::getTitle, primaryClassificationName);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public EduSubject existSecondaryEduSubject(String primaryClassificationId, String secondaryClassificationName) {
        LambdaQueryWrapper<EduSubject> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EduSubject::getParentId, primaryClassificationId).eq(EduSubject::getTitle, secondaryClassificationName);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public CommonResult listEduSubject() {
        LambdaQueryWrapper<EduSubject> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(EduSubject::getParentId).orderByAsc(EduSubject::getSort).orderByDesc(EduSubject::getGmtCreate);
        return CommonResult.ok().data("list",baseMapper.selectList(queryWrapper));
    }
    @Override
    public CommonResult treeListSubjectClassification() {
        // 查询所有课程一级分类数据
        LambdaQueryWrapper<EduSubject> primaryWrapper = new LambdaQueryWrapper<>();
        primaryWrapper.eq(EduSubject::getParentId, "0").orderByAsc(EduSubject::getSort);
        List<EduSubject> primaryEduSubjectList = baseMapper.selectList(primaryWrapper);
        // 查询所有课程二级分类数据  ---> 封装最终数据
        List<SubjectParent> finalDataList = primaryEduSubjectList.stream().map(item -> {
            SubjectParent spc = new SubjectParent();
            // 将每个课程的数据复制到课程分类实体中
            BeanUtils.copyProperties(item, spc);
            // 根据类一个一级分类的Id查询二级分类列表
            LambdaQueryWrapper<EduSubject> secondaryWrapper = new LambdaQueryWrapper<>();
            secondaryWrapper.eq(EduSubject::getParentId, item.getId());
            List<EduSubject> secondarySubjectList = baseMapper.selectList(secondaryWrapper);
            // 创建一个二级分类集合
            List<Subject> sscList = new ArrayList<>();
            // 遍历每一个二级分类
            for (EduSubject eduSubject : secondarySubjectList) {
                Subject ssc = new Subject();
                BeanUtils.copyProperties(eduSubject, ssc);
                // 添加到二级分类集合中
                sscList.add(ssc);
            }
            spc.setChildren(sscList);
            return spc;
        }).collect(Collectors.toList());
        return CommonResult.ok().data("list", finalDataList);
    }
}
