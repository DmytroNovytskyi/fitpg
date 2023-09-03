package com.fitpg.controller;

import com.fitpg.dto.UserDto;
import com.fitpg.validation.group.OnCreate;
import com.fitpg.validation.group.OnUpdate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * This interface defines controller interface for managing users.
 */
@Validated
@RequestMapping("/users")
public interface UserController {

    String SORT_BY_REGEX = "id|username|email";
    String ORDER_REGEX = "asc|desc";

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
    String getSortedPage(@RequestParam(value = "page", defaultValue = "0")
                         @Min(value = 0, message = "{users.getSortedPage.page.min}") int page,
                         @RequestParam(value = "size", defaultValue = "5")
                         @Min(value = 1, message = "{users.getSortedPage.size.min}") int size,
                         @RequestParam(value = "sortBy", defaultValue = "id")
                         @Pattern(regexp = SORT_BY_REGEX,
                                 message = "{users.getSortedPage.sortBy.pattern}") String sortBy,
                         @RequestParam(value = "order", defaultValue = "asc")
                         @Pattern(regexp = ORDER_REGEX,
                                 message = "{users.getSortedPage.order.pattern}") String order,
                         Model model);

    /**
     * Displays create form page.
     *
     * @return create user form
     */
    @GetMapping("/create")
    String create(Model model);

    /**
     * Creates a new user.
     *
     * @param user the user to create
     * @return redirects to users page
     */
    @PostMapping("/create")
    String create(@ModelAttribute("user") @Validated(OnCreate.class) UserDto user,
                  BindingResult bindingResult, Model model);

    /**
     * Displays update form page.
     *
     * @param id the ID of the user to update
     * @return update user form
     */
    @GetMapping("/{id}")
    String update(@Validated @PathVariable("id") @Min(value = 1, message = "{users.update.id.min}") long id,
                  Model model);

    /**
     * Updates an existing user.
     *
     * @param user the user to update
     * @return redirects to users page
     */
    @PutMapping("/{id}")
    String update(@Validated @PathVariable("id") @Min(value = 1, message = "{users.update.id.min}") long id,
                  @ModelAttribute("user") @Validated(OnUpdate.class) UserDto user,
                  BindingResult bindingResult, Model model);

    /**
     * Deletes a user with the specified ID.
     *
     * @param id the ID of the user to delete
     * @return redirects to users page
     */
    @DeleteMapping("/{id}")
    String delete(@PathVariable("id") @Min(value = 1, message = "{users.delete.id.min}") long id);
}
