package com.ght.graduationsys.dao;

import com.ght.graduationsys.entity.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionMapper {
    public List<Permission> getPermissionsByRid(int rid);
}
