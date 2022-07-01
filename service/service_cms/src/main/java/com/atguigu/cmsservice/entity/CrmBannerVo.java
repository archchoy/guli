package com.atguigu.cmsservice.entity;
import lombok.Data;

import java.io.Serializable;

@Data
public class CrmBannerVo implements Serializable {
    private String id;
    private String title;
    private String imageUrl;
    private String linkUrl;
    private Integer sort;
}
