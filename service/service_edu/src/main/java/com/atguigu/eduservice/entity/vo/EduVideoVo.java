package com.atguigu.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "课时信息")
@Data
public class EduVideoVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private Boolean isFree;
    private Integer sort;
    private String chapterId;
    private String courseId;
    private String videoSourceId;
    private String videoOriginalName;
}
