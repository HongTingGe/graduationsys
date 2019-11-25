package com.ght.graduationsys.service.impl;

import com.ght.graduationsys.dao.CollegeMapper;
import com.ght.graduationsys.entity.College;
import com.ght.graduationsys.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeMapper collegeMapper;

    @Override
    public List<College> getAllColleges() {
        return collegeMapper.getAllColleges();
    }
}
