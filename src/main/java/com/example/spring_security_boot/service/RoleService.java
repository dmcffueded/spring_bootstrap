package com.example.spring_security_boot.service;

import com.example.spring_security_boot.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    void saveRole(Role role);

    Role getRole(Long id);

    void deleteRole(Long id);
}
