package com.fitpg.controller;

import com.fitpg.dto.ExerciseInfoDto;
import com.fitpg.service.ExerciseInfoService;
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
@RequestMapping("/exercise-infos")
@RequiredArgsConstructor
public class ExerciseInfoController {

    private final static String SORT_BY_REGEX = "id|name";

    private final static String ORDER_REGEX = "asc|desc";

    private final ExerciseInfoService exerciseInfoService;

    private final MuscleGroupService muscleGroupService;

    @GetMapping
    public String getSortedPage(@RequestParam(value = "page", defaultValue = "1")
                                @Min(value = 1, message = "{exerciseInfos.getSortedPage.page.min}") int page,
                                @RequestParam(value = "size", defaultValue = "5")
                                @Min(value = 1, message = "{exerciseInfos.getSortedPage.size.min}") int size,
                                @RequestParam(value = "sortBy", defaultValue = "id")
                                @Pattern(regexp = SORT_BY_REGEX,
                                        message = "{exerciseInfos.getSortedPage.sortBy.pattern}") String sortBy,
                                @RequestParam(value = "order", defaultValue = "asc")
                                @Pattern(regexp = ORDER_REGEX,
                                        message = "{exerciseInfos.getSortedPage.order.pattern}") String order,
                                Model model) {
        Page<ExerciseInfoDto> exerciseInfoDtoPage = exerciseInfoService.getSortedPage(page - 1, size, sortBy, order);
        model.addAttribute("exerciseInfos", exerciseInfoDtoPage.getContent());
        model.addAttribute("currentPage", exerciseInfoDtoPage.getNumber() + 1);
        model.addAttribute("totalPages", exerciseInfoDtoPage.getTotalPages());
        model.addAttribute("pageSize", exerciseInfoDtoPage.getSize());
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
