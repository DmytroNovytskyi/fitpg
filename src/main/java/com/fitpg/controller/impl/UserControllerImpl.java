package com.fitpg.controller.impl;

import com.fitpg.controller.UserController;
import com.fitpg.dto.UserDto;
import com.fitpg.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

/**
 * RestController class for handling requests related to users.
 */
@Controller
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    /**
     * The UserService object that will be used to manage users.
     */
    private final UserService userService;

    @Override
    public String getSortedPage(int page, int size, String sortBy, String order, Model model) {
        model.addAttribute("page", userService.getSortedPage(page, size, sortBy, order));
        return "users-list";
    }

    @Override
    public String create(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "users-create";
    }

    @Override
    public String create(UserDto user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "users-create";
        }
        userService.create(user);
        return "redirect:/users";
    }

    @Override
    public String update(long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "users-update";
    }

    @Override
    public String update(long id, UserDto user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            user.setUsername(userService.getById(id).getUsername());
            model.addAttribute("user", user);
            return "users-update";
        }
        userService.update(user);
        return "redirect:/users";
    }

    @Override
    public String delete(long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }
}
