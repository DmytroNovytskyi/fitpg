package com.fitpg.service;

import com.fitpg.dto.ExerciseDto;

public interface ExerciseService {

    ExerciseDto getById(long id);

    ExerciseDto create(ExerciseDto exerciseDto, long workoutId);

    ExerciseDto update(ExerciseDto exerciseDto);

    void deleteById(long id);
}
