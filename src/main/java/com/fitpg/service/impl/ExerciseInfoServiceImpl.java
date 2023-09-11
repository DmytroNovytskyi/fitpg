package com.fitpg.service.impl;

import com.fitpg.dto.ExerciseInfoDto;
import com.fitpg.mapper.ExerciseInfoMapper;
import com.fitpg.repository.ExerciseInfoRepository;
import com.fitpg.service.ExerciseInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseInfoServiceImpl implements ExerciseInfoService {

    private final ExerciseInfoRepository exerciseInfoRepository;

    private final ExerciseInfoMapper exerciseInfoMapper;

    @Transactional
    @Override
    public List<ExerciseInfoDto> getAll() {
        return exerciseInfoRepository.findAll().stream().map(exerciseInfoMapper::mapExerciseInfoDto).toList();
    }

    @Transactional
    @Override
    public ExerciseInfoDto getById(long id) {
        return null;
    }

    @Transactional
    @Override
    public ExerciseInfoDto create(ExerciseInfoDto exerciseInfoDto) {
        return null;
    }

    @Transactional
    @Override
    public ExerciseInfoDto update(ExerciseInfoDto exerciseInfoDto) {
        return null;
    }

    @Transactional
    @Override
    public void deleteById(long id) {

    }
}
