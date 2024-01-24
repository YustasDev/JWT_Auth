package com.example.jwt_auth.controllers;


import com.example.jwt_auth.domain.JwtRequest;
import com.example.jwt_auth.domain.JwtResponse;
import com.example.jwt_auth.domain.RefreshJwtRequest;
import com.example.jwt_auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.message.AuthException;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) throws AuthException {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("token")
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) throws AuthException {
        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @PostMapping("refresh")
    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest request) throws AuthException {
        final JwtResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

}
