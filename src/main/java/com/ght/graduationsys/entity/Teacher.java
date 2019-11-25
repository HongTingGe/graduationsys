package com.ght.graduationsys.entity;

import lombok.Data;

import java.util.List;
@Data
public class Teacher extends User {
    private String id;
    private College college;
    private Major major;
    private String name;
    private String password;
    private String email;
    private String phone;
    private List<Role> roleList;

}
