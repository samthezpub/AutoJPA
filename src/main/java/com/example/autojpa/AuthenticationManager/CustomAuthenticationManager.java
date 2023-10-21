package com.example.autojpa.AuthenticationManager;

import com.example.autojpa.Entity.UserEntity;
import com.example.autojpa.Exception.CustomAuthenticationException;
import com.example.autojpa.Interface.AuthenticationManager;
import com.example.autojpa.Service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    public CustomAuthenticationManager(UserServiceImpl userService) {
        this.userService = userService;
    }

    public CustomAuthenticationManager() {
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();


        for (UserEntity user : userService.findAll()) {
            if (user.getUsername().equals(login) && user.getPassword().equals(password)) {
                return new UsernamePasswordAuthenticationToken(login, password);
            }
        }
        throw new BadCredentialsException("Неверное имя пользователя или пароль!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
