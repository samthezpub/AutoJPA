package com.example.autojpa.Service;

import com.example.autojpa.Entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException;
    void save(UserEntity user);

    List<UserEntity> findAll();
    Optional<UserEntity> findUser(Long id);

    void update(UserEntity user);

    void delete(Long id);


}
