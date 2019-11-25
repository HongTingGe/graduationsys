package com.ght.graduationsys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class PermissionModule {
    private int id;
    private String name;
    private String icon;
    private String url;
    private int status;//1正常 0冻结
    private List<Permission> list;

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"name\":\"" + name + '\"' +
                ", \"icon\":\"" + icon + '\"' +
                ", \"url\":\"" + url + '\"' +
                ", \"status\":" + status +
                ", \"list\":" + list +
                '}';
    }
}
