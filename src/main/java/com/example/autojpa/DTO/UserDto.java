package com.example.autojpa.DTO;

import jakarta.annotation.Nullable;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class UserDto {
    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String confirmPassword;
}
