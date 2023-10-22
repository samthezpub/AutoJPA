package com.example.autojpa.Controller;

import com.example.autojpa.DTO.UserDto;
import com.example.autojpa.Entity.UserEntity;
import com.example.autojpa.Exception.ConfirmPasswordException;
import com.example.autojpa.Exception.UsernameException;
import com.example.autojpa.Service.impl.UserDetailsServiceImpl;
import com.example.autojpa.Service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;



    @PostMapping("/login/confirm") // POST-метод для обработки входа
    public String performLogin(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpServletRequest request, HttpServletResponse response) {
        // Попробуйте аутентифицировать пользователя с использованием вашего UserDetailsService
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);


        if (userDetails != null && userDetails.getPassword().equals(password)) {
            // Успешная аутентификация, установите пользователя в сессию
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return "redirect:/"; // Перенаправление на главную страницу после успешного входа
        } else {
            // Ошибка аутентификации, отобразите сообщение об ошибке
            model.addAttribute("error", true);
            return "redirect:/login?error=true"; // Перенаправление на страницу входа с сообщением об ошибке
        }
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
