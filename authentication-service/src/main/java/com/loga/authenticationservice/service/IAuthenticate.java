package com.loga.authenticationservice.service;

import com.loga.authenticationservice.entity.AuthSession;
import com.loga.authenticationservice.entity.User;

import java.util.List;

public interface IAuthenticate {
    AuthSession register(User user);
    List<User> allUser();
    AuthSession authenticate(User user);
    AuthSession authenticate(String token);
    void logout(String token);
    void edit(User user, Long id);
    void delete(Long id);
}