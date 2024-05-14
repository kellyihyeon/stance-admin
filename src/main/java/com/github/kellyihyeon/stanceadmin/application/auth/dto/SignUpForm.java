package com.github.kellyihyeon.stanceadmin.application.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record SignUpForm(
        @Email
        @NotBlank(message = "이메일을 입력해 주세요.")
        String email,

        @NotBlank(message = "비밀번호를 입력해 주세요.")
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!#$%&?])[A-Za-z\\d!#$%&?]{8,18}$")
        String password,

        @NotBlank(message = "이름을 입력해 주세요.")
        String name
) { }
