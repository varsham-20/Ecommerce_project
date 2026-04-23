package com.example.user_and_authentication_service.controller;

import com.example.user_and_authentication_service.entity.User;
import com.example.user_and_authentication_service.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User create(@RequestBody User user){
       return userService.create(user);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable int id){
        return userService.getById(id);
    }

    @GetMapping
    public List<User> getAll(@RequestBody User user){
        return userService.getAll();
    }

    @PutMapping("/{id}")
    public User update(@PathVariable int id, @RequestBody User user){
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        userService.delete(id);
        return "User deleted successfully";
    }
}
