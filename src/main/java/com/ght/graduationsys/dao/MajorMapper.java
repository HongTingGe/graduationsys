package com.ght.graduationsys.dao;

import com.ght.graduationsys.entity.Major;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MajorMapper {
    public List<Major> getAllMajors();
    public List<Major> getMajorsByCid();//根据学院ID获取专业
    public String getDirectorIdByMajorName(String name);
    public Major getMajorById(int id);
    public String getDirectorIdById(int id);//根据学院Id获取系主任
}
