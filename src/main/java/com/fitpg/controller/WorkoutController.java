package com.fitpg.controller;

import com.fitpg.security.SecurityUtil;
import com.fitpg.service.WorkoutService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Controller
@RequestMapping("/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final static String SORT_BY_REGEX = "date";

    private final static String ORDER_REGEX = "asc|desc";

    private final WorkoutService workoutService;

    @GetMapping
    public String getSortedPage(@RequestParam(value = "page", defaultValue = "0")
                                @Min(value = 0, message = "{workouts.getSortedPage.page.min}") int page,
                                @RequestParam(value = "size", defaultValue = "5")
                                @Min(value = 1, message = "{workouts.getSortedPage.size.min}") int size,
                                @RequestParam(value = "sortBy", defaultValue = "date")
                                @Pattern(regexp = SORT_BY_REGEX,
                                        message = "{workouts.getSortedPage.sortBy.pattern}") String sortBy,
                                @RequestParam(value = "order", defaultValue = "asc")
                                @Pattern(regexp = ORDER_REGEX,
                                        message = "{workouts.getSortedPage.order.pattern}") String order,
                                Model model) {
        model.addAttribute("page",
                workoutService.getSortedPageForUser(page, size, sortBy, order,
                        SecurityUtil.getSessionUser()));
        return "workouts-list";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") @Min(value = 1, message = "{workouts.delete.id.min}") long id) {
        workoutService.deleteById(id);
        return "redirect:/workouts";
    }
}
