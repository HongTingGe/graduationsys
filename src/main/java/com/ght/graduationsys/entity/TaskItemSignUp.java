package com.ght.graduationsys.entity;

import lombok.Data;

import java.util.List;

@Data
public class TaskItemSignUp {
    private String id;
    private TaskItemDeclare taskItemDeclare;
    private List<Student> studentList;
    private TeacherBasicInfo teacher;
    private String detail;
    private int status;
    private String msg;
    private String createtime;

}
