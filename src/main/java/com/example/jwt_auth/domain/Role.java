package com.example.jwt_auth.domain;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {

    ADMIN("ADMIN"),
    USER("USER");

    private final String variable;

    @Override
    public String getAuthority() {
        return variable;
    }
}
