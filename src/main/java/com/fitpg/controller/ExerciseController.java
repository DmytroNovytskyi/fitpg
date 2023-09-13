package com.fitpg.controller;

import com.fitpg.dto.ExerciseDto;
import com.fitpg.model.Unit;
import com.fitpg.service.ExerciseInfoService;
import com.fitpg.service.ExerciseService;
import com.fitpg.service.MuscleGroupService;
import com.fitpg.service.util.FitPGUtil;
import com.fitpg.validation.group.OnExercise;
import com.fitpg.validation.group.OnUpdate;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Controller
@RequestMapping("/exercises")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    private final MuscleGroupService muscleGroupService;

    private final ExerciseInfoService exerciseInfoService;

    @GetMapping("/{id}")
    public String updatePage(@Validated @PathVariable("id") @Min(value = 1, message = "{exercises.update.id.min}") long id,
                             Model model) {
        ExerciseDto exercise = exerciseService.getById(id);
        model.addAttribute("exercise", exercise);
        model.addAttribute("allMuscleGroups", muscleGroupService.getAll());
        model.addAttribute("allExerciseInfos",
                FitPGUtil.mapExerciseToShow(exerciseInfoService.getAll(),
                        exercise.getExerciseInfo().getMuscleGroups()));
        model.addAttribute("allUnits", Unit.values());
        return "exercises-update";
    }

    @PutMapping("/{id}")
    public String update(@Validated @PathVariable("id") @Min(value = 1, message = "{exercises.update.id.min}") long id,
                         @ModelAttribute("exercise") @Validated({OnUpdate.class, OnExercise.class}) ExerciseDto exercise,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("exercise", exercise);
            model.addAttribute("allMuscleGroups", muscleGroupService.getAll());
            model.addAttribute("allExerciseInfos",
                    FitPGUtil.mapExerciseToShow(exerciseInfoService.getAll(),
                            exercise.getExerciseInfo().getMuscleGroups()));
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
