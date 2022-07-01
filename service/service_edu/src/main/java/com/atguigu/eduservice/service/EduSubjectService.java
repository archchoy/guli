package com.atguigu.eduservice.service;

import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author RofoX
 * @since 2022-05-13
 */
public interface EduSubjectService extends IService<EduSubject> {

    /**
     * 是否存在一级分类课程
     * @param primaryClassificationName 一级分类课程名称
     * @return 一级分类课程
     */
    EduSubject existsPrimaryEduSubject(String primaryClassificationName);

    /**
     * 是否存在二级分类
     * @param primaryClassificationId 一级分类课程Id
     * @param secondaryClassificationName 二级分类课程名称
     * @return 二课分类课程
     */
    EduSubject existSecondaryEduSubject(String primaryClassificationId,String secondaryClassificationName);

    /**
     * 获取树形结构课程分类信息
     */
    CommonResult treeListSubjectClassification();

    /**
     * 课程分类列表
     */
    CommonResult listEduSubject();

}
