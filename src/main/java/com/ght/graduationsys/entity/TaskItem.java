package com.ght.graduationsys.entity;

import lombok.Data;

import java.util.Date;
@Data
public class TaskItem {
    private String id;
    private String sid;//负责人学号
    private String sname;//负责人名字
    private String majorClazz;//负责人所在专业班级
    private String name;//课题名
    private String type;
    private String teacherid;//指导老师
    private String directorid;//系主任ID
    private String members;//成员
    private int status;
    private String msg;
    private String details;
    private Date createtime;
    private Date checktime;

}
