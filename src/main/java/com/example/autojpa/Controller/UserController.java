package com.example.autojpa.Controller;

import com.example.autojpa.DTO.UserDto;
import com.example.autojpa.Entity.UserEntity;
import com.example.autojpa.Exception.ConfirmPasswordException;
import com.example.autojpa.Exception.UsernameException;
import com.example.autojpa.Service.impl.UserDetailsServiceImpl;
import com.example.autojpa.Service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping(value = {"/", "/welcome"})
    public String welcomePage(Model model) {
        return "login";
    }
    @GetMapping(value = "/login")
    public String loginPage(Model model) {
        return "login";
    }

    @GetMapping(value = "/logoutSuccessful")
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }


//    @GetMapping("/register")
//    public String showRegistration(WebRequest request, Model model) {
//        UserDto userDto = new UserDto();
//        model.addAttribute("user", userDto);
//        return "security/register";
//    }
//
//    @PostMapping("/register/confirm")
//    public RedirectView confirmRegistration(UserDto userDto) {
//        try {
//            for (UserEntity user : userService.findAll()) {
//                if (user.getUsername().equals(userDto.getUsername())) {
//                    throw new UsernameException("Такой логин уже зарегестрирован!");
//                }
//            }
//        } catch (UsernameException e) {
//            return new RedirectView("/register");
//        }
//
//        try {
//            if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
//                throw new ConfirmPasswordException("Пароли не равны");
//            }
//        } catch (ConfirmPasswordException e) {
//            return new RedirectView("/register");
//        }
//
//        UserEntity user = new UserEntity();
//        user.setId(userDto.getId());
//        user.setUsername(userDto.getUsername());
//        user.setEncrytedPassword(userDto.getPassword());
//
//        userService.save(user);
//        return new RedirectView("/");
//    }
}
