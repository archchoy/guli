package com.atguigu.eduservice.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("课程等级分类")
public class SubjectName {
    @ApiModelProperty("课程信息一级分类名称")
    @ExcelProperty(index = 0)   // 对应Excel表格的第一列
    private String primaryClassificationName;
    @ApiModelProperty("课程信息二级分类名称")
    @ExcelProperty(index = 1)   // 对应Excel表格的第二列
    private String secondaryClassificationName;
}
