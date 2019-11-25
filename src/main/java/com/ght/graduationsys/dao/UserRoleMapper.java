package com.ght.graduationsys.dao;

import com.ght.graduationsys.entity.Role;

import java.util.List;

public interface UserRoleMapper {
    public List<Role> getRolesByUid(int id);
}
