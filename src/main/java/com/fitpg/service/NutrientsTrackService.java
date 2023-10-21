package com.fitpg.service;

import com.fitpg.dto.NutrientsTrackDto;
import org.springframework.data.domain.Page;

public interface NutrientsTrackService {

    NutrientsTrackDto getById(long id);

    Page<NutrientsTrackDto> getSortedPage(int page, int size, String sortBy, String order);

    NutrientsTrackDto create(NutrientsTrackDto weightTrackDto);

    NutrientsTrackDto update(NutrientsTrackDto weightTrackDto);

    void deleteById(long id);
}
