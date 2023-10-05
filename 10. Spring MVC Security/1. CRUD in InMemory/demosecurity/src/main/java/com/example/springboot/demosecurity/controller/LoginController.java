package com.example.springboot.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/showMyLoginPage")
    // This @GetMapping("/showMyLoginPage"), is based on information from our SpringSecurityConfig file, so those two things have to match up.
    public String showMyLoginPage() {
        return "fancy-login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }
}
