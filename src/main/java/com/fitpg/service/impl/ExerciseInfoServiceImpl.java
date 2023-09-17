package com.fitpg.service.impl;

import com.fitpg.dto.ExerciseInfoDto;
import com.fitpg.exception.ExerciseInfoNotFoundException;
import com.fitpg.mapper.ExerciseInfoMapper;
import com.fitpg.model.ExerciseInfo;
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
        ExerciseInfo exerciseInfo = exerciseInfoRepository.findById(id)
                .orElseThrow(ExerciseInfoNotFoundException::new);
        return exerciseInfoMapper.mapExerciseInfoDto(exerciseInfo);
    }

    @Transactional
    @Override
    public ExerciseInfoDto create(ExerciseInfoDto exerciseInfoDto) {
        ExerciseInfo exerciseInfo = exerciseInfoMapper.mapExerciseInfo(exerciseInfoDto);
        return exerciseInfoMapper.mapExerciseInfoDto(exerciseInfoRepository.save(exerciseInfo));
    }

    @Transactional
    @Override
    public ExerciseInfoDto update(ExerciseInfoDto exerciseInfoDto) {
        ExerciseInfo updating = exerciseInfoMapper.mapExerciseInfo(exerciseInfoDto);
        ExerciseInfo persisted = exerciseInfoRepository.findById(updating.getId())
                .orElseThrow(ExerciseInfoNotFoundException::new);
        exerciseInfoMapper.mapPresentFields(persisted, updating);
        return exerciseInfoMapper.mapExerciseInfoDto(exerciseInfoRepository.save(persisted));
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        ExerciseInfo exerciseInfo = exerciseInfoRepository.findById(id)
                .orElseThrow(ExerciseInfoNotFoundException::new);
        exerciseInfoRepository.delete(exerciseInfo);
    }
}
