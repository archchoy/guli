package com.atguigu.eduservice.service;

import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.vo.EduChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author RofoX
 * @since 2022-05-14
 */
public interface EduChapterService extends IService<EduChapter> {


    /**
     * 获取所有章节和小节信息
     * @param courseId 课程Id
     */
    List<EduChapterVo> listChapterAndVideo(String courseId);


    /**
     * 保存章节信息
     * @param eduChapterVo 章节信息
     */
    CommonResult saveEduChapter(EduChapterVo eduChapterVo);

    /**
     * 修改章节信息
     * @param eduChapterVo 章节信息
     */
    CommonResult updateChapter(EduChapterVo eduChapterVo);

    /**
     * 删除章节信息
     * @param id 章节Id
     */
    CommonResult removeChapter(String id);

    CommonResult getChapterById(String id);
}
