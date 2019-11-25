package com.ght.graduationsys.entity;

import lombok.Data;



@Data
public class Permission {
    private int id;
    private String name;
    private String alias;
    private int pmid;
    private String url;
    private int status;

}
