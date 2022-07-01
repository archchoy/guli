package com.atguigu.serviceoss.common;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class OSSUploadFileUtil {
    public static String upload(MultipartFile file){
        String fileName=null;
        String FILE_URL;
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null) {
            String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") );
            UUID uuid = UUID.randomUUID();
            // "guli_parent/avatar/" --> OSS目录
            // uuid + suffix  文件名和和后缀名
            fileName = "guli_parent/avatar/" +uuid + suffix;
        }
        OSS ossClient = new OSSClientBuilder()
                .build(OSSAccessInfo.END_POINT, OSSAccessInfo.ACCESS_KEY_ID, OSSAccessInfo.ACCESS_KEY_SECRET);
        try {
            ossClient.putObject(OSSAccessInfo.BUCKET_NAME, fileName, file.getInputStream());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ossClient.shutdown();
        }
        // 完整的文件路径
        FILE_URL = "https://" + OSSAccessInfo.BUCKET_NAME+ "." + OSSAccessInfo.END_POINT +"/"+ fileName;
        return FILE_URL;
    }
}
