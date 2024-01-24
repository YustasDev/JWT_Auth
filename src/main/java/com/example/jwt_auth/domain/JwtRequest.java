package com.example.jwt_auth.domain;


import lombok.Data;

@Data
public class JwtRequest {
    private String login;
    private String password;
}
