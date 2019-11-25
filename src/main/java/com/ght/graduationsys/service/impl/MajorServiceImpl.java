package com.ght.graduationsys.service.impl;

import com.ght.graduationsys.dao.MajorMapper;
import com.ght.graduationsys.entity.Major;
import com.ght.graduationsys.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MajorServiceImpl implements MajorService {

    @Autowired
    private MajorMapper majorMapper;

    @Override
    public List<Major> getAllMajors() {
        return majorMapper.getAllMajors();
    }

    @Override
    public List<Major> getMajorsByCid() {
        return majorMapper.getMajorsByCid();
    }

    @Override
    public String getDirectorIdByMajorName(String name) {
        return majorMapper.getDirectorIdByMajorName(name);
    }

    @Override
    public String getDirectorIdById(int id) {
        return majorMapper.getDirectorIdById(id);
    }
}
