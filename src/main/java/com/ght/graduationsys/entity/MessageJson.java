package com.ght.graduationsys.entity;

import lombok.Data;

import java.util.Date;

@Data
public class MessageJson {
    private String from;
    private String content;
    private Date createtime;
}
