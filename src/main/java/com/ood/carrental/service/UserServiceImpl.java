package com.ood.carrental.service;

import com.ood.carrental.User;
import com.ood.carrental.exception.ResourceNotFound;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class UserServiceImpl implements UserService {

    Set<User> users;
    UserServiceImpl(Set<User> users) {
        this.users = users;
    }

    @Override
    public User save(User user) {
        users.add(user);
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {

        if(username == null) {
            throw new IllegalArgumentException("Username cannot be null");
        }

        return Optional.ofNullable(users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFound("Username not found with username: " + username)));
    }

    @Override
    public User remove(Long id) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User remove(User user) {
        return null;
    }
}
