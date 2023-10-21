package com.fitpg.service;

import com.fitpg.dto.MuscleGroupDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MuscleGroupService {

    MuscleGroupDto getById(long id);

    List<MuscleGroupDto> getAll();

    Page<MuscleGroupDto> getSortedPage(int page, int size, String sortBy, String order);

    MuscleGroupDto create(MuscleGroupDto muscleGroupDto);

    MuscleGroupDto update(MuscleGroupDto muscleGroupDto);

    void deleteById(long id);
}
