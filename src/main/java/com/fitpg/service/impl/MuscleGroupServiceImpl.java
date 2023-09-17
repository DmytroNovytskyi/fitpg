package com.fitpg.service.impl;

import com.fitpg.dto.MuscleGroupDto;
import com.fitpg.exception.MuscleGroupNotFoundException;
import com.fitpg.mapper.MuscleGroupMapper;
import com.fitpg.model.MuscleGroup;
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
        MuscleGroup muscleGroup = muscleGroupRepository.findById(id)
                .orElseThrow(MuscleGroupNotFoundException::new);
        return muscleGroupMapper.mapMuscleGroupDto(muscleGroup);
    }

    @Transactional
    @Override
    public MuscleGroupDto create(MuscleGroupDto muscleGroupDto) {
        MuscleGroup muscleGroup = muscleGroupMapper.mapMuscleGroup(muscleGroupDto);
        return muscleGroupMapper.mapMuscleGroupDto(muscleGroupRepository.save(muscleGroup));
    }

    @Transactional
    @Override
    public MuscleGroupDto update(MuscleGroupDto muscleGroupDto) {
        MuscleGroup updating = muscleGroupMapper.mapMuscleGroup(muscleGroupDto);
        MuscleGroup persisted = muscleGroupRepository.findById(updating.getId())
                .orElseThrow(MuscleGroupNotFoundException::new);
        muscleGroupMapper.mapPresentFields(persisted, updating);
        return muscleGroupMapper.mapMuscleGroupDto(muscleGroupRepository.save(persisted));
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        MuscleGroup muscleGroup = muscleGroupRepository.findById(id)
                .orElseThrow(MuscleGroupNotFoundException::new);
        muscleGroup.clearExerciseInfosRelationship();
        muscleGroupRepository.delete(muscleGroup);
    }
}
