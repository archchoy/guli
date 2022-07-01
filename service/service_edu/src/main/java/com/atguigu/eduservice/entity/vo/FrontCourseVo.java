package com.atguigu.eduservice.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FrontCourseVo {
    private String title;   // 课程名称
    private String subjectId;   // 课程一级分类id
    private String subjectParentId;
    private boolean orderByBuyCount;     // 根据课程浏览量
    private boolean orderByPriceDesc;   // 根据课程价格降序
    private boolean orderByTimeDesc;    //根据时间降序
}
