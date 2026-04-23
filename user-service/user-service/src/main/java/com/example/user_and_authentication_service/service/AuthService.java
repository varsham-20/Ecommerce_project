package com.example.user_and_authentication_service.service;

import com.example.user_and_authentication_service.entity.User;

public interface AuthService {

    User register(User user);

    String login(String username, String password);

}