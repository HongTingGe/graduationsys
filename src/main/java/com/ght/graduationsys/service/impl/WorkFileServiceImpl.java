package com.ght.graduationsys.service.impl;

import com.ght.graduationsys.dao.WorkFileMapper;
import com.ght.graduationsys.entity.WorkFile;
import com.ght.graduationsys.service.WorkFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkFileServiceImpl implements WorkFileService {

    @Autowired
    private WorkFileMapper workFileMapper;

    @Override
    public void addWorkFile(WorkFile workFile) {
        workFileMapper.addWorkFile(workFile);
    }

    @Override
    public List<WorkFile> getWorkFilesByMajorId(int majorid) {
        return workFileMapper.getWorkFilesByMajorId(majorid);
    }
}
