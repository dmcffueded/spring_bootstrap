package com.example.spring_security_boot.service;

import com.example.spring_security_boot.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();
    void addUser(User user);
    User getUser(long id);
    void updateUser(User user);
    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
    void deleteUser(long id);
    void addInitUsers();
}
