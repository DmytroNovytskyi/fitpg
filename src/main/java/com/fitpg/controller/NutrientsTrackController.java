package com.fitpg.controller;

import com.fitpg.dto.NutrientsTrackDto;
import com.fitpg.service.NutrientsTrackService;
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

import java.text.SimpleDateFormat;
import java.util.List;

@Validated
@Controller
@RequestMapping("/nutrients-tracks")
@RequiredArgsConstructor
public class NutrientsTrackController {

    private final static String SORT_BY_REGEX = "date";

    private final static String ORDER_REGEX = "asc|desc";

    public static final String DATE_FORMAT = "dd-MM-yyyy";

    private final NutrientsTrackService nutrientsTrackService;

    @GetMapping
    public String getSortedPage(@RequestParam(value = "page", defaultValue = "1")
                                @Min(value = 1, message = "{nutrientsTrack.getSortedPage.page.min}") int page,
                                @RequestParam(value = "size", defaultValue = "7")
                                @Min(value = 1, message = "{nutrientsTrack.getSortedPage.size.min}") int size,
                                @RequestParam(value = "sortBy", defaultValue = "date")
                                @Pattern(regexp = SORT_BY_REGEX,
                                        message = "{nutrientsTrack.getSortedPage.sortBy.pattern}") String sortBy,
                                @RequestParam(value = "order", defaultValue = "asc")
                                @Pattern(regexp = ORDER_REGEX,
                                        message = "{nutrientsTrack.getSortedPage.order.pattern}") String order,
                                Model model) {
        Page<NutrientsTrackDto> nutrientsTrackDtoPage = nutrientsTrackService.getSortedPage(page - 1, size, sortBy, order);
        model.addAttribute("nutrientsTracks", nutrientsTrackDtoPage.getContent());
        model.addAttribute("currentPage", nutrientsTrackDtoPage.getNumber() + 1);
        model.addAttribute("totalPages", nutrientsTrackDtoPage.getTotalPages());
        model.addAttribute("pageOrder", order);
        model.addAttribute("pageSortBy", sortBy);
        model.addAttribute("pageSize", nutrientsTrackDtoPage.getSize());
        mapChartData(model, nutrientsTrackDtoPage);
        return "nutrients-tracks-list";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        NutrientsTrackDto nutrientsTrack = new NutrientsTrackDto();
        model.addAttribute("nutrientsTrack", nutrientsTrack);
        return "nutrients-tracks-create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("nutrientsTrack") @Validated(OnCreate.class) NutrientsTrackDto nutrientsTrack,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("nutrientsTrack", nutrientsTrack);
            return "nutrients-tracks-create";
        }
        nutrientsTrackService.create(nutrientsTrack);
        return "redirect:/nutrients-tracks";
    }

    @GetMapping("/{id}")
    public String updatePage(@Validated @PathVariable("id") @Min(value = 1, message = "{nutrientsTrack.update.id.min}") long id,
                             Model model) {
        model.addAttribute("nutrientsTrack", nutrientsTrackService.getById(id));
        return "nutrients-tracks-update";
    }

    @PutMapping("/{id}")
    public String update(@Validated @PathVariable("id") @Min(value = 1, message = "{nutrientsTrack.update.id.min}") long id,
                         @ModelAttribute("nutrientsTrack") @Validated(OnUpdate.class) NutrientsTrackDto nutrientsTrack,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("nutrientsTrack", nutrientsTrack);
            return "nutrients-tracks-update";
        }
        nutrientsTrackService.update(nutrientsTrack);
        return "redirect:/nutrients-tracks";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") @Min(value = 1, message = "{nutrientsTrack.delete.id.min}") long id) {
        nutrientsTrackService.deleteById(id);
        return "redirect:/nutrients-tracks";
    }

    private void mapChartData(Model model, Page<NutrientsTrackDto> nutrientsTrackDtoPage) {
        List<String> chartLabels = nutrientsTrackDtoPage.stream()
                .map(nt -> new SimpleDateFormat(DATE_FORMAT).format(nt.getDate()))
                .toList();
        List<Integer> fatsChartDataSets = nutrientsTrackDtoPage.stream().map(NutrientsTrackDto::getFats).toList();
        List<Integer> carbohydratesChartDataSets = nutrientsTrackDtoPage.stream()
                .map(NutrientsTrackDto::getCarbohydrates).toList();
        List<Integer> proteinChartDataSets = nutrientsTrackDtoPage.stream().map(NutrientsTrackDto::getProtein).toList();
        List<Integer> caloriesChartDataSets = nutrientsTrackDtoPage.stream().map(NutrientsTrackDto::getCalories).toList();
        model.addAttribute("chartLabels", chartLabels);
        model.addAttribute("fatsChartDataSets", fatsChartDataSets);
        model.addAttribute("carbohydratesChartDataSets", carbohydratesChartDataSets);
        model.addAttribute("proteinChartDataSets", proteinChartDataSets);
        model.addAttribute("caloriesChartDataSets", caloriesChartDataSets);
    }
}
