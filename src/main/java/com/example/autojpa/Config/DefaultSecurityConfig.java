package com.example.autojpa.Config;

import com.example.autojpa.AuthenticationManager.CustomAuthenticationManager;
import com.example.autojpa.Service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class DefaultSecurityConfig {

    @Autowired
    private CustomAuthenticationManager authenticationManager;


    @Autowired
    public DefaultSecurityConfig(CustomAuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public DefaultSecurityConfig() {
    }

    @Bean
    public CustomAuthenticationManager authenticationManager() {
        return new CustomAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/login", "/login/confirm").permitAll()
                                .requestMatchers("/register", "/register/confirm").permitAll()

                )
                .formLogin(login -> login
                        .loginPage("/login").permitAll()
                        .successForwardUrl("/") // Перенаправление после успешного входа
                        .defaultSuccessUrl("/")
                );

        return http.build();


    }


    @Bean
    @ConditionalOnMissingBean(UserDetailsService.class)
    InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(8);
        String userPassword = encoder.encode("password");
        String dispatchPassword = encoder.encode("dpassword");
        String driverPassword = encoder.encode("driverpassword");

        return new InMemoryUserDetailsManager(
                User.withUsername("user").password("{bcrypt}" + userPassword).roles("USER").build(),
                User.withUsername("dispatch").password("{bcrypt}" + dispatchPassword).roles("DISPATCH").build(),
                User.withUsername("driver").password("{bcrypt}" + driverPassword).roles("DRIVER").build()
        );
    }

    @Bean
    @ConditionalOnMissingBean(AuthenticationEventPublisher.class)
    DefaultAuthenticationEventPublisher defaultAuthenticationEventPublisher(ApplicationEventPublisher delegate) {
        return new DefaultAuthenticationEventPublisher(delegate);
    }

}
