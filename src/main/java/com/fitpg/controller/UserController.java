package com.fitpg.controller;

import com.fitpg.dto.UserDto;
import com.fitpg.service.UserService;
import com.fitpg.validation.group.OnCreate;
import com.fitpg.validation.group.OnUpdate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Defines controller for managing users.
 */
@Validated
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final static String SORT_BY_REGEX = "id|username|email";

    private final static String ORDER_REGEX = "asc|desc";

    /**
     * The UserService object that will be used for managing users.
     */
    private final UserService userService;

    /**
     * Retrieves a sorted and paginated list of users
     *
     * @param page   the page number (0-based) of the results to retrieve, "0" by default
     * @param size   the maximum number (1-based) of results per page, "1" by default
     * @param sortBy the field to sort the results by (id, username, or email), "id" by default
     * @param order  the sort order (asc or desc), "asc" by default
     * @return a page of users sorted and filtered according to the specified parameters
     */
    @GetMapping
    public String getSortedPage(@RequestParam(value = "page", defaultValue = "0")
                                @Min(value = 0, message = "{users.getSortedPage.page.min}") int page,
                                @RequestParam(value = "size", defaultValue = "5")
                                @Min(value = 1, message = "{users.getSortedPage.size.min}") int size,
                                @RequestParam(value = "sortBy", defaultValue = "id")
                                @Pattern(regexp = SORT_BY_REGEX,
                                        message = "{users.getSortedPage.sortBy.pattern}") String sortBy,
                                @RequestParam(value = "order", defaultValue = "asc")
                                @Pattern(regexp = ORDER_REGEX,
                                        message = "{users.getSortedPage.order.pattern}") String order,
                                Model model) {
        model.addAttribute("page", userService.getSortedPage(page, size, sortBy, order));
        return "users-list";
    }

    /**
     * Displays create form page.
     *
     * @return create user form
     */
    @GetMapping("/create")
    public String createPage(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        model.addAttribute("roles", userService.getAllRoles());
        return "users-create";
    }

    /**
     * Creates a new user.
     *
     * @param user the user to create
     * @return redirects to users page
     */
    @PostMapping("/create")
    public String create(@ModelAttribute("user") @Validated(OnCreate.class) UserDto user,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("roles", userService.getAllRoles());
            return "users-create";
        }
        userService.create(user);
        return "redirect:/users";
    }

    /**
     * Displays update form page.
     *
     * @param id the ID of the user to update
     * @return update user form
     */
    @GetMapping("/{id}")
    public String updatePage(@Validated @PathVariable("id") @Min(value = 1, message = "{users.update.id.min}") long id,
                             Model model) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("roles", userService.getAllRoles());
        return "users-update";
    }

    /**
     * Updates an existing user.
     *
     * @param id   the ID of the user to update
     * @param user the user to update
     * @return redirects to users page
     */
    @PutMapping("/{id}")
    public String update(@Validated @PathVariable("id") @Min(value = 1, message = "{users.update.id.min}") long id,
                         @ModelAttribute("user") @Validated(OnUpdate.class) UserDto user,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            user.setUsername(userService.getById(id).getUsername());
            model.addAttribute("user", user);
            model.addAttribute("roles", userService.getAllRoles());
            return "users-update";
        }
        userService.update(user);
        return "redirect:/users";
    }

    /**
     * Deletes a user with the specified ID.
     *
     * @param id the ID of the user to delete
     * @return redirects to users page
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") @Min(value = 1, message = "{users.delete.id.min}") long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }
}
