package com.atguigu.servicevod;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.atguigu.servicevod.utils.InitVodClientUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VodTest {

    @Test
    public void testGetPlayUrl() throws ClientException {
        DefaultAcsClient client = InitVodClientUtils.initVodClient("LTAI5tMgrBKer7G3Ad3z7nvR", "01uJWcl5kZdXxCxHvR9LSbBttLSYi0");
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId("c6eccc901c6a40b983b27aa3bfc320e2");
        GetPlayInfoResponse response;
        response = client.getAcsResponse(request);
        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            String playURL = playInfo.getPlayURL();
            System.out.println("视频地址: " + playURL);
        }
    }

    @Test
    public void testGetPlayAuth() throws ClientException {
        DefaultAcsClient client = InitVodClientUtils.initVodClient("LTAI5tMgrBKer7G3Ad3z7nvR", "01uJWcl5kZdXxCxHvR9LSbBttLSYi0");
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId("c6eccc901c6a40b983b27aa3bfc320e2");
        GetVideoPlayAuthResponse acsResponse = client.getAcsResponse(request);
        String playAuth = acsResponse.getPlayAuth();
        System.out.println(playAuth);

    }


    //        static void testUploadVideo(String accessKeyId, String accessKeySecret, String title, String fileName) {
    @Test
    public void testUploadVideo() {
        String accessKeyId = "LTAI5tMgrBKer7G3Ad3z7nvR";
        String accessKeySecret = "01uJWcl5kZdXxCxHvR9LSbBttLSYi0";
        String title = "ScreenCatchTest.mp4";      // 上传后文件的名称
        String fileName = "D:/test.mp4";       // 本地文件的路径和名称
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
        request.setPartSize(2 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }
}
