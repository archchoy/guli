package com.atguigu.eduservice.controller;


import com.alibaba.excel.EasyExcel;
import com.atguigu.commonutils.result.CommonResult;
import com.atguigu.eduservice.entity.SubjectName;
import com.atguigu.eduservice.listener.ExcelSubjectClassificationListener;
import com.atguigu.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author RofoX
 * @since 2022-05-13
 */
@Api(tags = "课程分类管理(后台系统)")
@CrossOrigin
@RestController
@RequestMapping("/edu-service/subject")
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @ApiOperation(" easyExcel 导入课程")
    @PostMapping("/importSubject")
    public CommonResult importSubject(MultipartFile file){
        try {
            EasyExcel.read(file.getInputStream(), SubjectName.class, new ExcelSubjectClassificationListener(eduSubjectService)).sheet().doRead();
            return CommonResult.ok().message("导入成功");
        }catch (Exception e){
            e.printStackTrace();
            return CommonResult.error().message("导入失败, 请确保数据格式正确");
        }
    }

    @ApiOperation("科目分类树形结构列表")
    @GetMapping("/treeList")
    public CommonResult treeListSubjectClassification(){
        return eduSubjectService.treeListSubjectClassification();
    }

    @ApiOperation("科目分类列表")
    @GetMapping("/list")
    public CommonResult listEduSubject(){
        return eduSubjectService.listEduSubject();
    }

}

