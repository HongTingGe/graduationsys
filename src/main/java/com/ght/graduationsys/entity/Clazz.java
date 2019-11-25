package com.ght.graduationsys.entity;

public class Clazz {
    private int id;
    private int mid;
    private String name;

    public Clazz(){}

    public Clazz(int id, int mid, String name) {
        this.id = id;
        this.mid = mid;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "id=" + id +
                ", mid=" + mid +
                ", name='" + name + '\'' +
                '}';
    }
}
