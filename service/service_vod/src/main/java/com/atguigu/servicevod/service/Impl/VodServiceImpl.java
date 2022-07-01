package com.atguigu.servicevod.service.Impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.servicevod.service.VodService;
import com.atguigu.servicevod.utils.AccessInfo;
import com.atguigu.servicevod.utils.InitVodClientUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VodServiceImpl implements VodService {
    @Override
    public CommonResult uploadByStream(MultipartFile file) {
        if (file != null) {
            String fileName = file.getOriginalFilename();
            String title = fileName.substring(0,fileName.lastIndexOf("."));
            try {
                UploadStreamRequest request = new UploadStreamRequest(AccessInfo.ACCESS_KEY, AccessInfo.ACCESS_SECRET, title, fileName, file.getInputStream());
                UploadVideoImpl uploader = new UploadVideoImpl();
                UploadStreamResponse response = uploader.uploadStream(request);
                String videoId = response.getVideoId();
                if (response.isSuccess()){
                    return CommonResult.ok().data("videoId",videoId);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return CommonResult.error().message("上传失败");
    }

    @Override
    public CommonResult removeVideo(String videoIds) {
        try {
            DefaultAcsClient acsClient = InitVodClientUtils.initVodClient(AccessInfo.ACCESS_KEY, AccessInfo.ACCESS_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(videoIds);
            acsClient.getAcsResponse(request);
            return CommonResult.ok().message("删除文件成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return CommonResult.error().message("删除文件失败");
    }


    @Override
    public CommonResult getPlayAuth(String id) {
        try {
            DefaultAcsClient client = InitVodClientUtils.initVodClient(AccessInfo.ACCESS_KEY, AccessInfo.ACCESS_SECRET);
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(id);
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            return CommonResult.ok().data("auth",response.getPlayAuth());
        }catch (Exception e){
            e.printStackTrace();
            return CommonResult.error().message("获取凭证失败");
        }

    }
}
