package com.fitpg.service;

import com.fitpg.dto.WeightTrackDto;
import org.springframework.data.domain.Page;

public interface WeightTrackService {

    WeightTrackDto getById(long id);

    Page<WeightTrackDto> getSortedPage(int page, int size, String sortBy, String order);

    WeightTrackDto create(WeightTrackDto weightTrackDto);

    WeightTrackDto update(WeightTrackDto weightTrackDto);

    void deleteById(long id);
}
