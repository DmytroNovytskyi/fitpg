package com.fitpg.controller;

import com.fitpg.dto.ExerciseInfoDto;
import com.fitpg.service.ExerciseInfoService;
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

@Validated
@Controller
@RequestMapping("/exercise-infos")
@RequiredArgsConstructor
public class ExerciseInfoController {

    private final ExerciseInfoService exerciseInfoService;

    private final MuscleGroupService muscleGroupService;

    @GetMapping
    public String getSortedPage(Model model) {
        model.addAttribute("exerciseInfos", exerciseInfoService.getAll());
        return "exercise-infos-list";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        ExerciseInfoDto exerciseInfo = new ExerciseInfoDto();
        model.addAttribute("exerciseInfo", exerciseInfo);
        model.addAttribute("allMuscleGroups", muscleGroupService.getAll());
        return "exercise-infos-create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("exerciseInfo") @Validated(OnCreate.class) ExerciseInfoDto exerciseInfo,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("exerciseInfo", exerciseInfo);
            model.addAttribute("allMuscleGroups", muscleGroupService.getAll());
            return "exercise-infos-create";
        }
        exerciseInfoService.create(exerciseInfo);
        return "redirect:/exercise-infos";
    }

    @GetMapping("/{id}")
    public String updatePage(@Validated @PathVariable("id") @Min(value = 1, message = "{exerciseInfos.update.id.min}") long id,
                             Model model) {
        model.addAttribute("exerciseInfo", exerciseInfoService.getById(id));
        model.addAttribute("allMuscleGroups", muscleGroupService.getAll());
        return "exercise-infos-update";
    }

    @PutMapping("/{id}")
    public String update(@Validated @PathVariable("id") @Min(value = 1, message = "{exerciseInfos.update.id.min}") long id,
                         @ModelAttribute("exerciseInfo") @Validated(OnUpdate.class) ExerciseInfoDto exerciseInfo,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("exerciseInfo", exerciseInfo);
            model.addAttribute("allMuscleGroups", muscleGroupService.getAll());
            return "exercise-infos-update";
        }
        exerciseInfoService.update(exerciseInfo);
        return "redirect:/exercise-infos";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") @Min(value = 1, message = "{exerciseInfos.delete.id.min}") long id) {
        exerciseInfoService.deleteById(id);
        return "redirect:/exercise-infos";
    }
}
