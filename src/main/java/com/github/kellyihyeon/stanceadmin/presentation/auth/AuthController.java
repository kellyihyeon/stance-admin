package com.github.kellyihyeon.stanceadmin.presentation.auth;

import com.github.kellyihyeon.stanceadmin.apis.AuthApi;
import com.github.kellyihyeon.stanceadmin.application.auth.AuthService;
import com.github.kellyihyeon.stanceadmin.models.CheckSystemAdminPermissionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthService service;

    @Override
    public ResponseEntity<Void> checkSystemAdminPermission(CheckSystemAdminPermissionRequest checkSystemAdminPermissionRequest) {
        service.checkSystemAdminPermission(checkSystemAdminPermissionRequest.getPermissionKey());
        return ResponseEntity.ok().build();
    }
}
