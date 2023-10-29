package com.fitpg.controller;

import com.fitpg.dto.WorkoutDto;
import com.fitpg.service.WorkoutService;
import com.fitpg.validation.group.OnCreate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public String getSortedPage(@RequestParam(value = "page", defaultValue = "1")
                                @Min(value = 1, message = "{workouts.getSortedPage.page.min}") int page,
                                @RequestParam(value = "size", defaultValue = "7")
                                @Min(value = 1, message = "{workouts.getSortedPage.size.min}") int size,
                                @RequestParam(value = "sortBy", defaultValue = "date")
                                @Pattern(regexp = SORT_BY_REGEX,
                                        message = "{workouts.getSortedPage.sortBy.pattern}") String sortBy,
                                @RequestParam(value = "order", defaultValue = "asc")
                                @Pattern(regexp = ORDER_REGEX,
                                        message = "{workouts.getSortedPage.order.pattern}") String order,
                                Model model) {
        Page<WorkoutDto> workoutDtoPage = workoutService.getSortedPage(page - 1, size, sortBy, order);
        model.addAttribute("workouts", workoutDtoPage.getContent());
        model.addAttribute("currentPage", workoutDtoPage.getNumber() + 1);
        model.addAttribute("totalPages", workoutDtoPage.getTotalPages());
        model.addAttribute("pageOrder", order);
        model.addAttribute("pageSortBy", sortBy);
        model.addAttribute("pageSize", workoutDtoPage.getSize());
        model.addAttribute("workout", new WorkoutDto());
        return "workouts-list";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("workout") @Validated(OnCreate.class) WorkoutDto workout) {
        workoutService.create(workout);
        return "redirect:/workouts";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") @Min(value = 1, message = "{workouts.delete.id.min}") long id) {
        workoutService.deleteById(id);
        return "redirect:/workouts";
    }
}
