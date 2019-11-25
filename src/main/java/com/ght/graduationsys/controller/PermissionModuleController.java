package com.ght.graduationsys.controller;

import com.ght.graduationsys.entity.PermissionModule;
import com.ght.graduationsys.service.PermissionModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/permissionModule")
public class PermissionModuleController {
    @Autowired
    private PermissionModuleService permissionModuleService;

    @RequestMapping("/add")
    public void addPermissionModule(PermissionModule pm){
        //permissionModuleService.addPermissionModule(pm);
    }

    @RequestMapping("/list")
    public List<PermissionModule> getPermissionModules(){
        return permissionModuleService.getPermissionModules();
    }


    @RequestMapping("/update")
    public List<PermissionModule> updatePermissionModuleByName(PermissionModule pm){
        permissionModuleService.updatePermissionModuleById(pm);
        return permissionModuleService.getPermissionModules();
    }


}
