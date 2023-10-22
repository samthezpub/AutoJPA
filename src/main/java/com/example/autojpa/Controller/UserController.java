package com.example.autojpa.Controller;

import com.example.autojpa.DTO.UserDto;
import com.example.autojpa.Entity.UserEntity;
import com.example.autojpa.Exception.ConfirmPasswordException;
import com.example.autojpa.Exception.UsernameException;
import com.example.autojpa.Service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {

    private UserServiceImpl userService;


    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }



    @GetMapping("/login")
    public String showLogin(Model model){
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "security/login";
    }

//    @PostMapping("/login/confirm")
//    public String  loginConfirm(UserEntity user){
//
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
//        Authentication authentication = authenticationManager.authenticate(token);
//
//        try {
//            // Попытка аутентификации с использованием CustomAuthenticationManager
//            authentication = authenticationManager.authenticate(authentication);
//
//            // Если аутентификация успешна
//            if (authentication.isAuthenticated()) {
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//                return "index";
//            }
//        } catch (AuthenticationException e) {
//            System.err.println("ОШИБКА");
//        }
//        return "Привет!";
//    }

    @GetMapping("/register")
    public String showRegistration(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "security/register";
    }

    @PostMapping("/register/confirm")
    public RedirectView confirmRegistration(UserDto userDto) {
        try {
            for (UserEntity user : userService.findAll()) {
                if (user.getUsername().equals(userDto.getUsername())) {
                    throw new UsernameException("Такой логин уже зарегестрирован!");
                }
            }
        } catch (UsernameException e) {
            return new RedirectView("/register");
        }

        try {
            if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
                throw new ConfirmPasswordException("Пароли не равны");
            }
        } catch (ConfirmPasswordException e) {
            return new RedirectView("/register");
        }

        UserEntity user = new UserEntity();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        userService.save(user);
        return new RedirectView("/");
    }
}
