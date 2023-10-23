package com.example.autojpa.Service.impl;

import com.example.autojpa.Entity.UserEntity;
import com.example.autojpa.Repository.UserRepository;
import com.example.autojpa.Repository.UserRoleRepository;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRoleRepository userRoleRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userOptional = this.userRepository.findUserAccount(username);


        if (!userOptional.isPresent()){
            log.error("User not found! " + username);
            throw new UsernameNotFoundException("User " + username + " not found in database");
        }

        UserEntity currentUser = userOptional.get();

        List<String> roleNames = userRoleRepository.getRoleNames(currentUser.getId());

        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roleNames!=null){
            grantList = roleNames.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }
        log.error(currentUser.toString());
        return new User(currentUser.getUserName(), currentUser.getEncrytedPassword(), grantList);
    }
}
