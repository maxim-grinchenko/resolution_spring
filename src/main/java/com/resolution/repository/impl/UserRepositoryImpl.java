package com.resolution.repository.impl;

import com.resolution.entity.User;
import com.resolution.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {


    @Override
    public void delete(User user) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public boolean existsById(long id) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(long id) {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public void saveAll(List<User> users) {

    }
}