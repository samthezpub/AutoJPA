package com.example.autojpa.DTO;


import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
public class UserDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String confirmPassword;

    public UserDto() {
    }
}
