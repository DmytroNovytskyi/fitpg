package com.fitpg.service;

import com.fitpg.dto.ExerciseInfoDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ExerciseInfoService {

    ExerciseInfoDto getById(long id);

    List<ExerciseInfoDto> getAll();

    Page<ExerciseInfoDto> getSortedPage(int page, int size, String sortBy, String order);

    ExerciseInfoDto create(ExerciseInfoDto exerciseInfoDto);

    ExerciseInfoDto update(ExerciseInfoDto exerciseInfoDto);

    void deleteById(long id);
}
