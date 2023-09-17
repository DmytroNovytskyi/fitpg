package com.fitpg.service.impl;

import com.fitpg.dto.ExerciseDto;
import com.fitpg.dto.WorkoutDto;
import com.fitpg.exception.ExerciseNotFoundException;
import com.fitpg.mapper.ExerciseMapper;
import com.fitpg.model.Exercise;
import com.fitpg.repository.ExerciseRepository;
import com.fitpg.service.ExerciseService;
import com.fitpg.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    private final WorkoutService workoutService;

    private final ExerciseMapper exerciseMapper;

    @Transactional
    @Override
    public ExerciseDto getById(long id) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(ExerciseNotFoundException::new);
        return exerciseMapper.mapExerciseDto(exercise);
    }

    @Transactional
    @Override
    public ExerciseDto create(ExerciseDto exerciseDto, long workoutId) {
        WorkoutDto workout = workoutService.getById(workoutId);
        workout.getExercises().add(exerciseDto);
        List<ExerciseDto> updatedExercises = workoutService.update(workout).getExercises();
        return updatedExercises.get(updatedExercises.size() - 1);
    }

    @Transactional
    @Override
    public ExerciseDto update(ExerciseDto exerciseDto) {
        Exercise updating = exerciseMapper.mapExercise(exerciseDto);
        Exercise persisted = exerciseRepository.findById(updating.getId()).orElseThrow(ExerciseNotFoundException::new);
        exerciseMapper.mapPresentFields(persisted, updating);
        return exerciseMapper.mapExerciseDto(exerciseRepository.save(persisted));
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(ExerciseNotFoundException::new);
        exerciseRepository.delete(exercise);
    }
}
