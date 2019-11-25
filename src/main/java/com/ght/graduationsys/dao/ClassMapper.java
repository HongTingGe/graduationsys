package com.ght.graduationsys.dao;

import com.ght.graduationsys.entity.Clazz;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassMapper {
    public List<Clazz> getClassesByMid(int mid);
}
