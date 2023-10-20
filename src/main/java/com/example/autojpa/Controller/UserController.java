package com.example.autojpa.Controller;

import com.example.autojpa.DTO.UserDto;
import com.example.autojpa.Entity.UserEntity;
import com.example.autojpa.Exception.ConfirmPasswordException;
import com.example.autojpa.Exception.UsernameException;
import com.example.autojpa.Service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public RedirectView login_confirm(){
        return new RedirectView("/");
    }


    @GetMapping("/registration")
    public String showRegistration(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registrer";
    }

    @PostMapping("/registration")
    public RedirectView confirmRegistration(UserDto userDto) {
        try {
            for (UserEntity user : userService.findAll()) {
                if (user.getUsername().equals(userDto.getUsername())) {
                    throw new UsernameException("Такой логин уже зарегестрирован!");
                }
            }
        } catch (UsernameException e) {
            return new RedirectView("/registration");
        }


        try {
            if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
                throw new ConfirmPasswordException("Пароли не равны");
            }
        } catch (ConfirmPasswordException e) {
            return new RedirectView("/registration");
        }

        UserEntity user = new UserEntity();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        userService.save(user);
        return new RedirectView("/registration");
    }
}
