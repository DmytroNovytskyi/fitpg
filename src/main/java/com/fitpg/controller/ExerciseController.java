package com.fitpg.controller;

import com.fitpg.dto.ExerciseDto;
import com.fitpg.dto.MuscleGroupDto;
import com.fitpg.model.Unit;
import com.fitpg.service.ExerciseInfoService;
import com.fitpg.service.ExerciseService;
import com.fitpg.service.MuscleGroupService;
import com.fitpg.validation.group.OnCreate;
import com.fitpg.validation.group.OnUpdate;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Controller
@RequestMapping("/exercises")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    private final MuscleGroupService muscleGroupService;

    private final ExerciseInfoService exerciseInfoService;

    @GetMapping("/create/{workoutId}")
    public String createPage(@Validated @PathVariable("workoutId") @Min(value = 1, message = "{exercises.create.workoutId.min}") long workoutId,
                             Model model) {
        List<MuscleGroupDto> muscleGroups = muscleGroupService.getAll();
        model.addAttribute("exercise", new ExerciseDto());
        model.addAttribute("allMuscleGroups", muscleGroups);
        model.addAttribute("allExerciseInfos", exerciseInfoService.getAll());
        model.addAttribute("allUnits", Unit.values());
        model.addAttribute("workoutId", workoutId);
        return "exercises-create";
    }

    @PostMapping("/create/{workoutId}")
    public String create(@Validated @PathVariable("workoutId") @Min(value = 1, message = "{exercises.create.workoutId.min}") long workoutId,
                         @ModelAttribute("exercise") @Validated(OnCreate.class) ExerciseDto exercise,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("exercise", exercise);
            model.addAttribute("allMuscleGroups", muscleGroupService.getAll());
            model.addAttribute("allExerciseInfos", exerciseInfoService.getAll());
            model.addAttribute("allUnits", Unit.values());
            model.addAttribute("workoutId", workoutId);
            return "exercises-create";
        }
        exerciseService.create(exercise, workoutId);
        return "redirect:/workouts";
    }

    @GetMapping("/{id}")
    public String updatePage(
            @Validated @PathVariable("id") @Min(value = 1, message = "{exercises.update.id.min}") long id,
            Model model) {
        ExerciseDto exercise = exerciseService.getById(id);
        model.addAttribute("exercise", exercise);
        model.addAttribute("allMuscleGroups", muscleGroupService.getAll());
        model.addAttribute("allExerciseInfos", exerciseInfoService.getAll());
        model.addAttribute("allUnits", Unit.values());
        return "exercises-update";
    }

    @PutMapping("/{id}")
    public String update(
            @Validated @PathVariable("id") @Min(value = 1, message = "{exercises.update.id.min}") long id,
            @ModelAttribute("exercise") @Validated(OnUpdate.class) ExerciseDto exercise,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("exercise", exercise);
            model.addAttribute("allMuscleGroups", muscleGroupService.getAll());
            model.addAttribute("allExerciseInfos", exerciseInfoService.getAll());
            model.addAttribute("allUnits", Unit.values());
            return "exercises-update";
        }
        exerciseService.update(exercise);
        return "redirect:/workouts";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") @Min(value = 1, message = "{exercises.delete.id.min}") long id) {
        exerciseService.deleteById(id);
        return "redirect:/workouts";
    }
}
