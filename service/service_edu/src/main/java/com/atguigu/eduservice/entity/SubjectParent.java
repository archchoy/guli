package com.atguigu.eduservice.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubjectParent {
    private String id;
    private String title;
    List<Subject> children = new ArrayList<>();
}
