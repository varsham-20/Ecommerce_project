package com.example.user_and_authentication_service.service;

import com.example.user_and_authentication_service.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    User create(User user);
    User getById(int id);
    List<User> getAll();
    User update(int id, User user);
    void delete(int id);
    UserDetails loadUserByUsername(String name);
}
