package com.ood.carrental.service;

import com.ood.carrental.model.User;

import java.util.Optional;

public interface UserService {

    User save(User user);
    Optional<User> findByUsername(String username);
    User remove(Long id);
    User update(User user);
    User remove(User user);
}
