package com.fitpg.controller;

import com.fitpg.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

@Validated
@Controller
@RequiredArgsConstructor
public class AuthController {
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
