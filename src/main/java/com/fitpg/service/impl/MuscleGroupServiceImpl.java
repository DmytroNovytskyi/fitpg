package com.fitpg.service.impl;

import com.fitpg.dto.MuscleGroupDto;
import com.fitpg.mapper.MuscleGroupMapper;
import com.fitpg.repository.MuscleGroupRepository;
import com.fitpg.service.MuscleGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MuscleGroupServiceImpl implements MuscleGroupService {

    private final MuscleGroupRepository muscleGroupRepository;

    private final MuscleGroupMapper muscleGroupMapper;

    @Transactional
    @Override
    public List<MuscleGroupDto> getAll() {
        return muscleGroupRepository.findAll().stream().map(muscleGroupMapper::mapMuscleGroupDto).toList();
    }

    @Transactional
    @Override
    public MuscleGroupDto getById(long id) {
        return null;
    }

    @Transactional
    @Override
    public MuscleGroupDto create(MuscleGroupDto muscleGroupDto) {
        return null;
    }

    @Transactional
    @Override
    public MuscleGroupDto update(MuscleGroupDto muscleGroupDto) {
        return null;
    }

    @Transactional
    @Override
    public void deleteById(long id) {

    }
}
