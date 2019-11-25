package com.ght.graduationsys.entity;

import lombok.Data;

@Data
public class Report {
    private String id;
    private String name;
    private String previewpath;
    private String realpath;
    private String studentid;
    private StudentBasicInfo studentBasicInfo;
    private String teacherid;
    private TeacherBasicInfo teacherBasicInfo;
    private String msg;
    private String createtime;
    private int status;

}
