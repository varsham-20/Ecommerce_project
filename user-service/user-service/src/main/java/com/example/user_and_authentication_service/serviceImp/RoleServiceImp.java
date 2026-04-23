package com.example.user_and_authentication_service.serviceImp;

import com.example.user_and_authentication_service.entity.Role;
import com.example.user_and_authentication_service.repository.RoleRepository;
import com.example.user_and_authentication_service.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImp implements RoleService {

    private final RoleRepository rolerepo;

    @Override
    public Role create(Role role) {
       return rolerepo.save(role);
    }

    @Override
    public Role getById(Long id, Role role) {
        return rolerepo.findById(id).orElseThrow(() -> new RuntimeException("id doesn't exists"));
    }

    @Override
    public List<Role> getAll() {
        return rolerepo.findAll();
    }

    @Override
    public Role update(Long id, Role role) {
        Role role1 = rolerepo.findById(id).orElseThrow(() -> new RuntimeException("id doesn't exists"));
        role1.setRoleName(role.getRoleName());
        return rolerepo.save(role1);
    }

    @Override
    public void delete(Long id) {
        Role role = rolerepo.findById(id).orElseThrow(() -> new RuntimeException("id doesn't exists"));
        rolerepo.delete(role);
    }
}
