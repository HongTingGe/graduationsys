package com.ght.graduationsys.entity;

import lombok.Data;

import java.util.List;

@Data
public class TaskItemDeclareJsonData {
    private int code;
    private String msg;
    private List<TaskItemDeclare> data;
    private long count;

    public TaskItemDeclareJsonData(long count,List<TaskItemDeclare> data){
        this.code=0;
        this.msg="";
        this.data=data;
        this.count=count;
    }
}
