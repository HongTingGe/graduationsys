package com.ght.graduationsys.dao;

import com.ght.graduationsys.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper {
    public Role getRoleById(int id);
}
