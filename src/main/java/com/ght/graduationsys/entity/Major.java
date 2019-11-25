package com.ght.graduationsys.entity;

import lombok.Data;

import java.util.List;
@Data
public class Major {
    private int id;
    private String name;
    private List<Teacher> teacherList;
    private List<Clazz> classList;
}
