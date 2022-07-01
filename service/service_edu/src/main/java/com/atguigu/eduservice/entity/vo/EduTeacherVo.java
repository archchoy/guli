package com.atguigu.eduservice.entity.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="EduTeacherVO 封装条件查询实体类", description="讲师条件")
public class EduTeacherVo{
    @ApiModelProperty("讲师名称")
    private String name;
    @ApiModelProperty("讲师资历")
    private String career;
    @ApiModelProperty("讲师简介")
    private String intro;
    @ApiModelProperty("讲师头衔")
    private Integer level;
    // 可以使用String来接受前端传来的参数, 无需经过类型装换
    @ApiModelProperty("条件查询讲师创建时间  --> 开始时间")
    private String gmtCreateBegin;      // 开始时间
    @ApiModelProperty("条件查询讲师创建时间  --> 结束时间")
    private String gmtCreateEnd;        // 结束时间
}
