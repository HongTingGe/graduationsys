package com.ght.graduationsys.entity;

import lombok.Data;

import java.util.List;

@Data
public class WorkFileJsonData {
    private int code;
    private String msg;
    private long count;
    private List<WorkFile> data;

    public WorkFileJsonData(long count,List<WorkFile> data){
        this.code=0;
        this.msg="";
        this.count=count;
        this.data=data;
    }
}
