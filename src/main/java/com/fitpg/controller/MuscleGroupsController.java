package com.fitpg.controller;

import com.fitpg.dto.ExerciseInfoDto;
import com.fitpg.dto.MuscleGroupDto;
import com.fitpg.service.MuscleGroupService;
import com.fitpg.validation.group.OnCreate;
import com.fitpg.validation.group.OnUpdate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    private final static String SORT_BY_REGEX = "id|name";

    private final static String ORDER_REGEX = "asc|desc";

    private final MuscleGroupService muscleGroupService;

    @GetMapping
    public String getSortedPage(@RequestParam(value = "page", defaultValue = "1")
                                    @Min(value = 1, message = "{muscleGroups.getSortedPage.page.min}") int page,
                                @RequestParam(value = "size", defaultValue = "5")
                                    @Min(value = 1, message = "{muscleGroups.getSortedPage.size.min}") int size,
                                @RequestParam(value = "sortBy", defaultValue = "id")
                                    @Pattern(regexp = SORT_BY_REGEX,
                                            message = "{muscleGroups.getSortedPage.sortBy.pattern}") String sortBy,
                                @RequestParam(value = "order", defaultValue = "asc")
                                    @Pattern(regexp = ORDER_REGEX,
                                            message = "{muscleGroups.getSortedPage.order.pattern}") String order,
                                Model model) {
        Page<MuscleGroupDto> muscleGroupDtoPage = muscleGroupService.getSortedPage(page - 1, size, sortBy, order);
        model.addAttribute("muscleGroups", muscleGroupDtoPage.getContent());
        model.addAttribute("currentPage", muscleGroupDtoPage.getNumber() + 1);
        model.addAttribute("totalPages", muscleGroupDtoPage.getTotalPages());
        model.addAttribute("pageSize", muscleGroupDtoPage.getSize());
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
