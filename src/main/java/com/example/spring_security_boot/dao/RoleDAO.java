package com.example.spring_security_boot.dao;


import com.example.spring_security_boot.model.Role;

import java.util.Set;

public interface RoleDAO {
    public Set<Role> getAllRoles();

    public void saveRole(Role role);

    public Role getRole(String name);

    public void deleteRole(String name);

}