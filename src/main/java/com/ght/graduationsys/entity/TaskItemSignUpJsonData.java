package com.ght.graduationsys.entity;

import lombok.Data;

import java.util.List;
@Data
public class TaskItemSignUpJsonData {
    private int code;
    private String msg;
    private List<TaskItemSignUpBasicInfo> data;
    private long count;

    public TaskItemSignUpJsonData(long count,List<TaskItemSignUpBasicInfo> data){
        this.code=0;
        this.msg="";
        this.data=data;
        this.count=count;
    }
}
