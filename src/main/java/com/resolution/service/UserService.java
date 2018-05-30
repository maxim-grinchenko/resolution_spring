package com.resolution.service;



import com.resolution.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findUserById(long id);

    User saveUser(User student);

    List<User> findAllUsers();

    void updateUser(User student);
}