package com.atguigu.eduservice.service.impl;

import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.vo.EduChapterVo;
import com.atguigu.eduservice.entity.vo.EduVideoVo;
import com.atguigu.eduservice.mapper.EduChapterMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author RofoX
 * @since 2022-05-14
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<EduChapterVo> listChapterAndVideo(String courseId) {
        // 根据课程Id查询章节列表
        LambdaQueryWrapper<EduChapter> chapterQueryWrapper = new LambdaQueryWrapper<>();
        chapterQueryWrapper.eq(EduChapter::getCourseId, courseId).orderByAsc(EduChapter::getSort);
        List<EduChapter> eduChapterList = baseMapper.selectList(chapterQueryWrapper);
        // 根据课程Id查询小节列表
        LambdaQueryWrapper<EduVideo> videoQueryWrapper = new LambdaQueryWrapper<>();
        videoQueryWrapper.eq(EduVideo::getCourseId, courseId).orderByAsc(EduVideo::getSort);
        List<EduVideo> eduVideoList = eduVideoService.list(videoQueryWrapper);
        // 数据封装
        List<EduChapterVo> chapterVoList = eduChapterList.stream().map((item) -> {
            EduChapterVo eduChapterVo = new EduChapterVo();
            BeanUtils.copyProperties(item, eduChapterVo);
            List<EduVideoVo> videoVoList = new ArrayList<>();
            for (EduVideo eduVideo : eduVideoList) {
                // 如果小节(EduVideo)的 ChapterId 和章节的Id相同,说明该小节有该章节所属
                if (item.getId().equals(eduVideo.getChapterId())) {
                    EduVideoVo eduVideoVo = new EduVideoVo();
                    BeanUtils.copyProperties(eduVideo, eduVideoVo);
                    videoVoList.add(eduVideoVo);
                }
            }
            eduChapterVo.setChildren(videoVoList);
            return eduChapterVo;
        }).collect(Collectors.toList());
        return chapterVoList;
    }


    @Override
    public CommonResult saveEduChapter(EduChapterVo eduChapterVo) {
        EduChapter eduChapter = new EduChapter();
        BeanUtils.copyProperties(eduChapterVo, eduChapter);
        int saveChapter = baseMapper.insert(eduChapter);
        if ( saveChapter > 0 ) {
            return CommonResult.ok().message("课程章节信息添加成功");
        }
        return CommonResult.error().message("课程章节信息添加失败");
    }

    @Override
    public CommonResult updateChapter(EduChapterVo eduChapterVo) {
        EduChapter chapter = new EduChapter();
        BeanUtils.copyProperties(eduChapterVo, chapter);
        int update = baseMapper.updateById(chapter);
        if ( update > 0 ) {
            return CommonResult.ok().message("课程章节信息修改成功");
        }
        return CommonResult.error().message("课程章节信息修改成功");
    }

    @Override
    public CommonResult removeChapter(String id) {
        LambdaQueryWrapper<EduVideo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EduVideo::getChapterId,id);
        int count = eduVideoService.count(queryWrapper);
        if ( count > 0 ){
            throw new GuliException(20001,"该章节下含小节内容,不能删除");
        }
        int remove = baseMapper.deleteById(id);
        if ( remove > 0 ){
            return CommonResult.ok().message("课程章节信息删除成功");
        }
        return CommonResult.error().message("课程章节信息删除失败");
    }

    @Override
    public CommonResult getChapterById(String id) {
        EduChapter eduChapter = baseMapper.selectById(id);
        if (eduChapter != null) {
            EduChapterVo chapterVo = new EduChapterVo();
            BeanUtils.copyProperties(eduChapter, chapterVo);
            return CommonResult.ok().data("chapter",eduChapter);
        }
        return CommonResult.error().message("没有找到章节信息");
    }
}
