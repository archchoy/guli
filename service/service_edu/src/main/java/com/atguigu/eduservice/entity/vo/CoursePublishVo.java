package com.atguigu.eduservice.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CoursePublishVo implements Serializable {
    private String title;
    private BigDecimal price;
    private Integer lessonNum;
    private String cover;
    private String teacherName;
    private String primarySubjectName;
    private String secondarySubjectName;
}
