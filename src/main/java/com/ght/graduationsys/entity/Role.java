package com.ght.graduationsys.entity;

import lombok.Data;

import java.util.List;

@Data
public class Role {
    private int id;
    private String name;
    private List<Permission> permissionList;


}
