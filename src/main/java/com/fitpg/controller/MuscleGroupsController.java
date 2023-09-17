package com.fitpg.controller;

import com.fitpg.dto.MuscleGroupDto;
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
@RequestMapping("/muscle-groups")
@RequiredArgsConstructor
public class MuscleGroupsController {

    private final MuscleGroupService muscleGroupService;

    @GetMapping
    public String getPage(Model model) {
        model.addAttribute("muscleGroups", muscleGroupService.getAll());
        return "muscle-groups-list";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        MuscleGroupDto muscleGroup = new MuscleGroupDto();
        model.addAttribute("muscleGroup", muscleGroup);
        return "muscle-groups-create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("muscleGroup") @Validated(OnCreate.class) MuscleGroupDto muscleGroup,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("muscleGroup", muscleGroup);
            return "muscle-groups-create";
        }
        muscleGroupService.create(muscleGroup);
        return "redirect:/muscle-groups";
    }

    @GetMapping("/{id}")
    public String updatePage(@Validated @PathVariable("id") @Min(value = 1, message = "{muscleGroups.update.id.min}") long id,
                             Model model) {
        model.addAttribute("muscleGroup", muscleGroupService.getById(id));
        return "muscle-groups-update";
    }

    @PutMapping("/{id}")
    public String update(@Validated @PathVariable("id") @Min(value = 1, message = "{muscleGroups.update.id.min}") long id,
                         @ModelAttribute("muscleGroup") @Validated(OnUpdate.class) MuscleGroupDto muscleGroup,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("muscleGroup", muscleGroup);
            return "muscle-groups-update";
        }
        muscleGroupService.update(muscleGroup);
        return "redirect:/muscle-groups";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") @Min(value = 1, message = "{muscleGroups.delete.id.min}") long id) {
        muscleGroupService.deleteById(id);
        return "redirect:/muscle-groups";
    }
}
