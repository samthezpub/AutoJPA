package com.example.autojpa.Service.impl;

import com.example.autojpa.Entity.UserEntity;
import com.example.autojpa.Repository.UserRepository;
import com.example.autojpa.Service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void save(UserEntity user) {

    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findUser(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(UserEntity user) {

    }

    @Override
    public void delete(Long id) {

    }
}
