package com.fitpg.controller;

import com.fitpg.dto.UserDto;
import com.fitpg.service.UserService;
import com.fitpg.validation.group.OnRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Validated
@Controller
@RequiredArgsConstructor
public class AuthController {

    /**
     * Default user registration role.
     */
    public static final String USER_REGISTRATION_ROLE = "USER";

    /**
     * The UserService object that will be used for registration.
     */
    private final UserService userService;

    /**
     * Displays login form page.
     *
     * @return login user form
     */
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    /**
     * Displays register form page.
     *
     * @return register user form
     */
    @GetMapping("/register")
    public String registerPage(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    /**
     * Creates a new user.
     *
     * @param user the user to create
     * @return redirects to users page
     */
    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Validated(OnRegister.class) UserDto user,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        user.setRoles(Set.of(USER_REGISTRATION_ROLE));
        userService.create(user);
        return "redirect:/login";
    }
}
