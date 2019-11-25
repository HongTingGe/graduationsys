package com.ght.graduationsys.service;

import com.ght.graduationsys.entity.WorkFile;

import java.util.List;

public interface WorkFileService {
    public void addWorkFile(WorkFile workFile);
    public List<WorkFile> getWorkFilesByMajorId(int majorid);
}
