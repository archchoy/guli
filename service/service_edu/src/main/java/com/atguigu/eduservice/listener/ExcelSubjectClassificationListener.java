package com.atguigu.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.SubjectName;
import com.atguigu.eduservice.service.EduSubjectService;
import com.atguigu.servicebase.exception.GuliException;
import org.apache.commons.lang3.StringUtils;

public class ExcelSubjectClassificationListener extends AnalysisEventListener<SubjectName> {
    public EduSubjectService eduSubjectService;

    public ExcelSubjectClassificationListener(){}

    // 监听器不受Spring管理  这里我们提供构造方法来注入service
    public ExcelSubjectClassificationListener(EduSubjectService eduSubjectService){
        this.eduSubjectService = eduSubjectService;

    }

    @Override
    public void invoke(SubjectName subjectName, AnalysisContext analysisContext) {
        // 如果文件数据为空 抛出业务异常
        if (subjectName == null){
            throw new GuliException(20001," Excel文件数据异常 ");
        }
        // 一级分类名称
        String primaryClassificationName = subjectName.getPrimaryClassificationName();
        // 二级分类名称
        String secondaryClassificationName = subjectName.getSecondaryClassificationName();
        if (StringUtils.isNotEmpty(primaryClassificationName)) {
            // 如果不存在一级分类就添加
            EduSubject existsPrimaryEduSubject = existsPrimaryEduSubject(primaryClassificationName);
            if (existsPrimaryEduSubject == null) {
                EduSubject eduSubject = new EduSubject();
                eduSubject.setParentId("0");
                eduSubject.setTitle(primaryClassificationName);
                eduSubjectService.save(eduSubject);
            }
            // 查询一级分类ID
            existsPrimaryEduSubject = eduSubjectService.existsPrimaryEduSubject(primaryClassificationName);
            String primaryEduSubjectId = existsPrimaryEduSubject.getId();
            // 查询是否存在二级分类
            EduSubject existSecondaryEduSubject = existSecondaryEduSubject(primaryEduSubjectId,secondaryClassificationName);
            // 不存在二级分类就添加
            if (existSecondaryEduSubject == null){
                EduSubject eduSubject = new EduSubject();
                // 为二级分类设置 parent_id ( 一级分类id )
                eduSubject.setParentId(primaryEduSubjectId);
                eduSubject.setTitle(secondaryClassificationName);
                eduSubjectService.save(eduSubject);
            }
        }else if(primaryClassificationName == null && StringUtils.isNotEmpty(secondaryClassificationName)){
            throw new GuliException(20001,"Excel数据格式错误 课程一级分类名称不能为空, 已暂停导入");
        }



    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
    // 查询是否存在一级分类
    public EduSubject existsPrimaryEduSubject(String primaryClassificationName){
        return eduSubjectService.existsPrimaryEduSubject(primaryClassificationName);
    }

    // 查询是否存在二级分类
    public EduSubject existSecondaryEduSubject(String primaryClassificationId,String secondaryClassificationName){
        return eduSubjectService.existSecondaryEduSubject(primaryClassificationId,secondaryClassificationName);
    }


}
