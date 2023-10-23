package com.example.autojpa.Service.impl;

import com.example.autojpa.Entity.UserEntity;
import com.example.autojpa.Repository.UserRepository;
import com.example.autojpa.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity user = this.userRepository.findUserAccount(userName).get();

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
        grantedAuthorities.add(grantedAuthority);

        return new User(user.getUserName(), user.getEncrytedPassword(), grantedAuthorities);
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
