package com.ght.graduationsys.entity;

import lombok.Data;

import java.util.List;

@Data
public class ReportJsonData {
    private int code;
    private String msg;
    private long count;
    private List<Report> data;

    public ReportJsonData(long count,List<Report> data){
        this.code=0;
        this.msg="";
        this.count=count;
        this.data=data;
    }
}
