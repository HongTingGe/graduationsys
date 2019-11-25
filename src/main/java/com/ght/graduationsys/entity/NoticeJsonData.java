package com.ght.graduationsys.entity;

import lombok.Data;

@Data
public class NoticeJsonData {

    private int errno;
    private String[] data;

    public NoticeJsonData(){}

    public NoticeJsonData(int errno , String[] data){
        this.errno = errno;
        this.data = data;
    }


    public static NoticeJsonData buildSuccess(String[] data){
        return new NoticeJsonData(0,data);
    }

    public static NoticeJsonData buildError(String[] data){
        return new NoticeJsonData(-1,data);
    }


}
