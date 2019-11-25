package com.ght.graduationsys.dao;

import com.ght.graduationsys.entity.Permission;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface PermissionMapper {
    public List<Permission> getPermissionsByPmid(int id);
}
