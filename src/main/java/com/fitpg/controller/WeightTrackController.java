package com.fitpg.controller;

import com.fitpg.dto.WeightTrackDto;
import com.fitpg.service.WeightTrackService;
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
@RequestMapping("/weight-tracks")
@RequiredArgsConstructor
public class WeightTrackController {

    private final static String SORT_BY_REGEX = "date";

    private final static String ORDER_REGEX = "asc|desc";

    public static final String DATE_FORMAT = "dd-MM-yyyy";

    private final WeightTrackService weightTrackService;

    @GetMapping
    public String getSortedPage(@RequestParam(value = "page", defaultValue = "1")
                                @Min(value = 1, message = "{weightTracks.getSortedPage.page.min}") int page,
                                @RequestParam(value = "size", defaultValue = "7")
                                @Min(value = 1, message = "{weightTracks.getSortedPage.size.min}") int size,
                                @RequestParam(value = "sortBy", defaultValue = "date")
                                @Pattern(regexp = SORT_BY_REGEX,
                                        message = "{weightTracks.getSortedPage.sortBy.pattern}") String sortBy,
                                @RequestParam(value = "order", defaultValue = "asc")
                                @Pattern(regexp = ORDER_REGEX,
                                        message = "{weightTracks.getSortedPage.order.pattern}") String order,
                                Model model) {
        Page<WeightTrackDto> weightTrackDtoPage = weightTrackService.getSortedPage(page - 1, size, sortBy, order);
        model.addAttribute("weightTracks", weightTrackDtoPage.getContent());
        model.addAttribute("currentPage", weightTrackDtoPage.getNumber() + 1);
        model.addAttribute("totalPages", weightTrackDtoPage.getTotalPages());
        model.addAttribute("pageSize", weightTrackDtoPage.getSize());
        mapChartData(model, weightTrackDtoPage);
        return "weight-tracks-list";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        WeightTrackDto weightTrack = new WeightTrackDto();
        model.addAttribute("weightTrack", weightTrack);
        return "weight-tracks-create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("weightTrack") @Validated(OnCreate.class) WeightTrackDto weightTrack,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("weightTrack", weightTrack);
            return "weight-tracks-create";
        }
        weightTrackService.create(weightTrack);
        return "redirect:/weight-tracks";
    }

    @GetMapping("/{id}")
    public String updatePage(@Validated @PathVariable("id") @Min(value = 1, message = "{weightTracks.update.id.min}") long id,
                             Model model) {
        model.addAttribute("weightTrack", weightTrackService.getById(id));
        return "weight-tracks-update";
    }

    @PutMapping("/{id}")
    public String update(@Validated @PathVariable("id") @Min(value = 1, message = "{weightTracks.update.id.min}") long id,
                         @ModelAttribute("weightTrack") @Validated(OnUpdate.class) WeightTrackDto weightTrack,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("weightTrack", weightTrack);
            return "weight-tracks-update";
        }
        weightTrackService.update(weightTrack);
        return "redirect:/weight-tracks";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") @Min(value = 1, message = "{weightTracks.delete.id.min}") long id) {
        weightTrackService.deleteById(id);
        return "redirect:/weight-tracks";
    }

    private void mapChartData(Model model, Page<WeightTrackDto> weightTrackDtoPage) {
        List<String> chartLabels = weightTrackDtoPage.stream()
                .map(wt -> new SimpleDateFormat(DATE_FORMAT).format(wt.getDate()))
                .toList();
        List<Double> chartDataSets = weightTrackDtoPage.stream().map(WeightTrackDto::getWeight).toList();
        model.addAttribute("chartLabels", chartLabels);
        model.addAttribute("chartDataSets", chartDataSets);
    }
}
