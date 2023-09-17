package com.fitpg.service;

import com.fitpg.dto.WorkoutDto;
import org.springframework.data.domain.Page;

public interface WorkoutService {

    WorkoutDto getById(long id);

    Page<WorkoutDto> getSortedPageForUser(int page, int size, String sortBy, String order, String username);

    WorkoutDto create(WorkoutDto workoutDto);

    WorkoutDto update(WorkoutDto workoutDto);

    void deleteById(long id);
}
