package com.ght.graduationsys.util;

import com.ght.graduationsys.entity.Permission;
import com.ght.graduationsys.entity.PermissionModule;
import com.ght.graduationsys.entity.Role;
import com.ght.graduationsys.service.PermissionModuleService;

import java.util.*;

public class MenuUtil {
    public static LinkedHashMap<PermissionModule, List<Permission>> getMenus
            (PermissionModuleService permissionModuleService, List<Role> roleList){

        LinkedHashMap<PermissionModule,List<Permission>> menuMap=new LinkedHashMap<>();



        for (Role role:roleList){
            List<Permission> permissionList=role.getPermissionList();//当前角色所有的权限permissionList
            for (Permission permission:permissionList){
                int pmid=permission.getPmid();
                PermissionModule permissionModule=permissionModuleService.getPermissionModuleById(pmid);//当前遍历的权限所属的权限模块
                PermissionModule pm2=null;
                if (menuMap.isEmpty()){
                    List<Permission> permissions=new ArrayList<>();
                    permissions.add(permission);
                    menuMap.put(permissionModule,permissions);
                }else{
                    Set<PermissionModule> pms=menuMap.keySet();//当前菜单所有保存的权限模块
                    boolean flag=false;
                    for (PermissionModule pm1 : pms){
                        if (pm1.getName().equals(permissionModule.getName())){
                            flag=true;
                            pm2=pm1;
                            break;
                        }else{
                            flag=false;
                        }
                    }


                    if (flag==false){
                        List<Permission> permissions=new ArrayList<>();
                        permissions.add(permission);
                        menuMap.put(permissionModule,permissions);
                    }else{
                        List<Permission> permissions=menuMap.get(pm2);
                        boolean flag1=false;
                        for (Permission p:permissions){
                            if (p.getName().equals(permission.getName())){
                                flag1=true;
                                break;
                            }else{
                                flag1=false;
                            }
                        }
                        if (flag1==false){
                            permissions.add(permission);
                        }
                    }
                }
            }
        }


        return menuMap;

    }
}
