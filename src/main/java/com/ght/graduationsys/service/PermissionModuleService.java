package com.ght.graduationsys.service;

import com.ght.graduationsys.entity.PermissionModule;

import java.util.List;

public interface PermissionModuleService {
    public void addPermissionModule(PermissionModule pm);
    public List<PermissionModule> getPermissionModules();
    public void updatePermissionModuleById(PermissionModule pm);
    public PermissionModule getPermissionModuleById(int id);
}
