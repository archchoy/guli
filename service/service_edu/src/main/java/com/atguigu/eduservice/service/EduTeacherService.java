package com.atguigu.eduservice.service;

import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.EduTeacherVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author RofoX
 * @since 2022-05-09
 */
public interface EduTeacherService extends IService<EduTeacher> {


    /**
     * 添加讲师
     * @param eduTeacher 讲师信息
     */
    CommonResult saveEduTeacher(EduTeacher eduTeacher);

    /**
     * 列出所有讲师
     * @return 讲师列表集合
     */
    CommonResult listEduTeacher();

    /**
     * 根据Id查询讲师
     * @param id 讲师Id
     * @return 讲师信息
     */
    CommonResult queryOneEduTeacher(String id);

    /**
     * 修改讲师
     * @param eduTeacher 讲师信息
     * @return 修改后的讲师
     */
    CommonResult updateEduTeacher(EduTeacher eduTeacher);


    /**
     * 删除讲师信息
     * @param id 讲师Id
     * @return 提示信息
     */
    CommonResult removeEduTeacher(String id);


    /**
     * 按照条件分页讲师
     * @param page 页码
     * @param limit 每页数量
     * @param eduTeacherVo 条件信息
     * @return 分页信息
     */
    CommonResult pageEduTeacher(Integer page, Integer limit, EduTeacherVo eduTeacherVo);

}
