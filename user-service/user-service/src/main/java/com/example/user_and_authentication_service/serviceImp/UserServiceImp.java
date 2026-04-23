package com.example.user_and_authentication_service.serviceImp;

import com.example.user_and_authentication_service.entity.User;
import com.example.user_and_authentication_service.repository.UserRepository;
import com.example.user_and_authentication_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService, UserDetailsService {

    private final UserRepository repo;

    @Override
    public User create(User user) {
        return repo.save(user);
    }

    @Override
    public User getById(int id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Id doesn't exists"));
    }

    @Override
    public List<User> getAll() {
        return repo.findAll();
    }

    @Override
    public User update(int id, User user) {
        User updatedUser = repo.findById(id).orElseThrow(() -> new RuntimeException("id doestn't exists"));
        updatedUser.setName(user.getName());
        updatedUser.setEmail(user.getEmail());
        return repo.save(updatedUser);
    }

    @Override
    public void delete(int id) {
        User user = repo.findById(id).orElseThrow(() -> new RuntimeException("id doesn't exists"));
        repo.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = repo.findByName(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole().getRoleName()))
        );
    }
}
