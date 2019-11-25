package com.ght.graduationsys.entity;

import lombok.Data;

@Data
public class TaskItemSignUpBasicInfo {
    private String id;
    private TaskItemDeclareBasicInfo taskItemDeclareBasicInfo;
    private StudentBasicInfo studentBasicInfo;
    private String directorid;
    private TeacherBasicInfo teacherBasicInfo;
    private String detail;
    private int status;
    private String msg;
    private String createtime;
    private int isprincipal;

}
