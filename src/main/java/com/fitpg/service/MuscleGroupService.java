package com.fitpg.service;

import com.fitpg.dto.MuscleGroupDto;

import java.util.List;

public interface MuscleGroupService {

    List<MuscleGroupDto> getAll();

    MuscleGroupDto getById(long id);

    MuscleGroupDto create(MuscleGroupDto muscleGroupDto);

    MuscleGroupDto update(MuscleGroupDto muscleGroupDto);

    void deleteById(long id);
}
