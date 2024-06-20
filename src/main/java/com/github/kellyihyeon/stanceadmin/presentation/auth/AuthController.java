package com.github.kellyihyeon.stanceadmin.presentation.auth;

import com.github.kellyihyeon.stanceadmin.application.auth.AuthService;
import com.github.kellyihyeon.stanceadmin.application.auth.dto.LoginForm;
import com.github.kellyihyeon.stanceadmin.application.auth.dto.SignUpForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @RequestBody @Valid LoginForm loginForm
    ) {
        authService.login(loginForm);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(
            @RequestBody @Valid SignUpForm signUpForm
    ) {
        authService.signUp(signUpForm.email(), signUpForm.password(), signUpForm.name());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
