package com.ght.graduationsys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class Student extends User {
    private String id;
    private College college;
    private Major major;
    private Clazz clazz;
    private String name;
    private String password;
    private String email;
    private String phone;
    private List<Role> roleList;
}
