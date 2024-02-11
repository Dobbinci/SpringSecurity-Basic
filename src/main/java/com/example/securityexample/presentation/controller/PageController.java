package com.example.securityexample.presentation.controller;


import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() { return "index"; }

    @GetMapping("/user")
    public String user() {
        return "userPage";
    }

    @GetMapping("/admin")
    public String admin() {
        return "adminPage";
    }

    @GetMapping("/login")
    public String login() {
        return "loginForm";
    }

    @GetMapping("/sign-up")
    public String signup() {
        return "signupForm";
    }

    @Secured("ADMIN")
    @GetMapping("/secret")
    public String secret() { return "secretPage";}


}
