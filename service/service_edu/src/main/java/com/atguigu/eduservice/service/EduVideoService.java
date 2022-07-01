package com.atguigu.eduservice.service;

import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.vo.EduVideoVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author RofoX
 * @since 2022-05-18
 */
public interface EduVideoService extends IService<EduVideo> {
    /**
     * 添加小节信息
     * @param eduVideoVo 小节信息
     */
    CommonResult saveVideo(EduVideoVo eduVideoVo);


    /**
     * 删除小节信息
     * @param id 小节Id
     */
    CommonResult removeVideo(String id);

    /**
     * 修改小节信息
     * @param eduVideoVo 课程小节信息
     */
    CommonResult updateVideo(EduVideoVo eduVideoVo);

    /**
     *
     * @param id
     * @return
     */
    CommonResult getVideoById(String id);
}
