package com.example.jwt_auth.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtResponse {

    private final String type = "Bearer";
    private String accessToken;
    private String refreshToken;
}
