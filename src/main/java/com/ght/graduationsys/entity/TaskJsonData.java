package com.ght.graduationsys.entity;

import lombok.Data;

import java.util.List;
@Data
public class TaskJsonData {
    private int code;
    private String msg;
    private long count;
    private List<Task> data;

    public TaskJsonData(long count,List<Task> data){
        this.code=0;
        this.msg="";
        this.count=count;
        this.data=data;
    }
}
