package com.example.jwt_auth.controllers;


import com.example.jwt_auth.domain.JwtAuthentication;
import com.example.jwt_auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class DefaultController {

        private final AuthService authService;

        @PreAuthorize("hasAuthority('USER')")
        @GetMapping("hello/user")
        public ResponseEntity<String> helloUser() {
            final JwtAuthentication authInfo = authService.getAuthInfo();
            return ResponseEntity.ok("Hello user " + authInfo.getPrincipal() + "!");
        }

        @PreAuthorize("hasAuthority('ADMIN')")
        @GetMapping("hello/admin")
        public ResponseEntity<String> helloAdmin() {
            final JwtAuthentication authInfo = authService.getAuthInfo();
            return ResponseEntity.ok("Hello admin " + authInfo.getPrincipal() + "!");
        }

    }