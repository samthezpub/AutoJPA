package com.example.autojpa.Service.impl;

import com.example.autojpa.Entity.UserEntity;
import com.example.autojpa.Repository.UserRepository;
import com.example.autojpa.Repository.UserRolesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRolesRepository userRolesRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity currentUser = this.userRepository.findUserAccount(username).orElseThrow(RuntimeException::new);

        if (currentUser == null){
            log.error("User not found! " + username);
            throw new UsernameNotFoundException("User " + username + " not found in database");
        }

        List<String> roleNames = userRolesRepository.getRoleNames(currentUser.getId());

        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roleNames!=null){
            grantList = roleNames.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }
        return new User(currentUser.getUsername(), currentUser.getEncryptedPassword(), grantList);
    }
}
