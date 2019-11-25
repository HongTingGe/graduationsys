package com.ght.graduationsys.dao;

import com.ght.graduationsys.entity.PermissionModule;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionModuleMapper {
    public void addPermissionModule(PermissionModule pm);
    public List<PermissionModule> getPermissionModules();
    public void updatePermissionModuleById(PermissionModule pm);
    public PermissionModule getPermissionModuleById(int id);
}
