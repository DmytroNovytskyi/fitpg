package com.fitpg.service;

import com.fitpg.dto.ExerciseInfoDto;

import java.util.List;

public interface ExerciseInfoService {

    List<ExerciseInfoDto> getAll();

    ExerciseInfoDto getById(long id);

    ExerciseInfoDto create(ExerciseInfoDto exerciseInfoDto);

    ExerciseInfoDto update(ExerciseInfoDto exerciseInfoDto);

    void deleteById(long id);
}
