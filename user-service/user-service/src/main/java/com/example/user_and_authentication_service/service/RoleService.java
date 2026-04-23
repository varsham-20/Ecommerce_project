package com.example.user_and_authentication_service.service;

import com.example.user_and_authentication_service.entity.Role;

import java.util.List;

public interface RoleService {
    Role create(Role role);
    Role getById(Long id, Role role);
    List<Role> getAll();
    Role update(Long id, Role role);
    void delete(Long id);
}
