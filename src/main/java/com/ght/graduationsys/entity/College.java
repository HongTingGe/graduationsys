package com.ght.graduationsys.entity;

import java.util.List;

public class College {
    private int id;
    private String name;
    private List<Major> majorList;

    public College(){}

    public College(int id, String name, List<Major> majorList) {
        this.id = id;
        this.name = name;
        this.majorList = majorList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Major> getMajorList() {
        return majorList;
    }

    public void setMajorList(List<Major> majorList) {
        this.majorList = majorList;
    }

    @Override
    public String toString() {
        return "College{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", majorList=" + majorList +
                '}';
    }
}
