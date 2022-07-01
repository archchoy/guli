package com.atguigu.eduservice.service.impl;

import com.atguigu.client.VodClient;
import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.vo.EduVideoVo;
import com.atguigu.eduservice.mapper.EduVideoMapper;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author RofoX
 * @since 2022-05-18
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;


    @Override
    public CommonResult saveVideo(EduVideoVo eduVideoVo) {
        EduVideo eduVideo = new EduVideo();
        BeanUtils.copyProperties(eduVideoVo,eduVideo);
        int insert = baseMapper.insert(eduVideo);
        if (insert > 0){
            return CommonResult.ok().message("添加课程小节成功");
        }
        return CommonResult.error().message("添加课程小节失败");
    }

    @Override
    public CommonResult removeVideo(String id) {
        // 删除视频
        EduVideo eduVideo = baseMapper.selectById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        if (StringUtils.isNotEmpty(videoSourceId)) {
            CommonResult commonResult = vodClient.removeVideoByIds(videoSourceId);
            if (commonResult.getCode() ==20001){
                throw new GuliException(20001,"删除视频失败");
            }
        }
        // 删除章节记录
        int delete = baseMapper.deleteById(id);
        if (delete > 0) {
            return CommonResult.ok().message("删除课程小节成功");
        }
        return CommonResult.error().message("删除课程小节失败");
    }

    @Override
    public CommonResult updateVideo(EduVideoVo eduVideoVo) {
        EduVideo eduVideo = new EduVideo();
        BeanUtils.copyProperties(eduVideoVo,eduVideo);
        int update = baseMapper.updateById(eduVideo);
        if (update > 0){
            return CommonResult.ok().message("课程小节信息修改成功");
        }
        return CommonResult.error().message("课程小节信息修改失败");
    }

    @Override
    public CommonResult getVideoById(String id) {
        EduVideo eduVideo = baseMapper.selectById(id);
        if (eduVideo != null){
            return CommonResult.ok().data("video",eduVideo);
        }
        return CommonResult.error().message("找不到小节信息");
    }
}
