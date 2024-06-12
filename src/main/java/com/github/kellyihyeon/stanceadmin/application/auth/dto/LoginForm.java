package com.github.kellyihyeon.stanceadmin.application.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginForm {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
