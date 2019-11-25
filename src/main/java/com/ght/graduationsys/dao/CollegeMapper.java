package com.ght.graduationsys.dao;

import com.ght.graduationsys.entity.College;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollegeMapper {
    public List<College> getAllColleges();
}
