package com.example.securityexample.presentation.request;


import lombok.Getter;

@Getter
public class SignUpRequest {

    private String username;

    private String password;

    private int age;
}
