package com.ght.graduationsys.service.impl;

import com.ght.graduationsys.dao.PermissionModuleMapper;
import com.ght.graduationsys.entity.PermissionModule;
import com.ght.graduationsys.service.PermissionModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionModuleServiceImpl implements PermissionModuleService {
    @Autowired
    private PermissionModuleMapper permissionModuleMapper;

    @Override
    public void addPermissionModule(PermissionModule pm) {
        permissionModuleMapper.addPermissionModule(pm);
    }

    @Override
    public List<PermissionModule> getPermissionModules() {
        return permissionModuleMapper.getPermissionModules();
    }

    @Override
    public void updatePermissionModuleById(PermissionModule pm) {
        permissionModuleMapper.updatePermissionModuleById(pm);
    }

    @Override
    public PermissionModule getPermissionModuleById(int id) {
        return permissionModuleMapper.getPermissionModuleById(id);
    }
}
