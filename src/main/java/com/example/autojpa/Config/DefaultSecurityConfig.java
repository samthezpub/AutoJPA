package com.example.autojpa.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static jakarta.servlet.DispatcherType.ERROR;
import static jakarta.servlet.DispatcherType.FORWARD;
import static org.springframework.security.authorization.AuthorizationManagers.allOf;

@EnableWebSecurity
@Configuration
public class DefaultSecurityConfig {

//    @Bean
//    SecurityFilterChain web(HttpSecurity http) throws Exception {
//        http
//                // ...
//                .authorizeHttpRequests(authorize -> authorize
//                        .dispatcherTypeMatchers(FORWARD, ERROR).permitAll()
//                        .requestMatchers("/register", "/login").permitAll()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                );
//
//        return http.build();
//    }

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
