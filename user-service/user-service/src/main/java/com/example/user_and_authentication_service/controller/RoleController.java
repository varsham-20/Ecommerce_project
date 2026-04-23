package com.example.user_and_authentication_service.controller;

import com.example.user_and_authentication_service.entity.Role;
import com.example.user_and_authentication_service.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private RoleService roleService;

    @PostMapping
    public Role create(@RequestBody Role role){
        return roleService.create(role);
    }

    @GetMapping("/{id}")
    public Role getById(@PathVariable Long id, @RequestBody Role role){
        return roleService.getById(id, role);
    }

    @GetMapping
    public List<Role> getAll(@RequestBody Role role){
        return roleService.getAll();
    }

    @PutMapping("/{id}")
    public Role update(@PathVariable Long id, @RequestBody Role role){
        return roleService.update(id, role);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        roleService.delete(id);
        return "deleted successfully";
    }
}
