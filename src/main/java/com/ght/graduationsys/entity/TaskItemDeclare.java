package com.ght.graduationsys.entity;

import lombok.Data;

@Data
public class TaskItemDeclare {
    private String id;
    private String name;
    private int typeid;
    private Task task;
    private String studentid;
    private StudentBasicInfo student;
    private int status;
    private String detail;
    private String msg;
    private String createtime;
    private String directorid;
    private boolean flag;

}
