package com.example.jwt_auth.controllers;


import com.example.jwt_auth.domain.JwtAuthentication;
import com.example.jwt_auth.service.AuthService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping("api")
public class DefaultController {

    private final Bucket bucket;

    @Autowired
    private AuthService authService;

    public DefaultController(@Value("${capacity.requests}") long capacity) {
        Bandwidth limit = Bandwidth.classic(capacity, Refill.greedy(capacity, Duration.ofMinutes(1)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

        @PreAuthorize("hasAuthority('USER')")
        @GetMapping("hello/user")
        public ResponseEntity<String> helloUser() {
            final JwtAuthentication authInfo = authService.getAuthInfo();
            return ResponseEntity.ok("Hello user " + authInfo.getPrincipal() + "!");
        }

        @PreAuthorize("hasAuthority('ADMIN')")
        @GetMapping("hello/admin")
        public ResponseEntity<String> helloAdmin() {

            String my_data = "it's OK";

            if (bucket.tryConsume(1)) {
                return ResponseEntity.status(HttpStatus.OK).body(my_data);
            }
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("TOO_MANY_REQUESTS");


//            final JwtAuthentication authInfo = authService.getAuthInfo();
//            return ResponseEntity.ok("Hello admin " + authInfo.getPrincipal() + "!");
        }

    }