package com.ght.graduationsys.dao;

import com.ght.graduationsys.entity.WorkFile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkFileMapper {
    public void addWorkFile(WorkFile workFile);
    public List<WorkFile> getWorkFilesByMajorId(int majorid);
}
