package com.ght.graduationsys.service;

import com.ght.graduationsys.entity.Major;

import java.util.List;

public interface MajorService {
    public List<Major> getAllMajors();
    public List<Major> getMajorsByCid();
    public String getDirectorIdByMajorName(String name);
    public String getDirectorIdById(int id);
}
