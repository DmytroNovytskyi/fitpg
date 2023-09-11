package com.fitpg.service.impl;

import com.fitpg.dto.ExerciseDto;
import com.fitpg.exception.ExerciseNotFoundException;
import com.fitpg.mapper.ExerciseMapper;
import com.fitpg.model.Exercise;
import com.fitpg.model.Workout;
import com.fitpg.repository.ExerciseRepository;
import com.fitpg.repository.WorkoutRepository;
import com.fitpg.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    private final WorkoutRepository workoutRepository;

    private final ExerciseMapper exerciseMapper;

    @Transactional
    @Override
    public ExerciseDto getById(long id) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(ExerciseNotFoundException::new);
        return exerciseMapper.mapExerciseDto(exercise);
    }

    @Transactional
    @Override
    public ExerciseDto create(ExerciseDto exerciseDto) {
        return null;
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
        Workout workout = exercise.getWorkout();
        workout.getExercises().remove(exercise);
        if (workout.getExercises().isEmpty()) {
            workoutRepository.delete(workout);
        } else {
            exerciseRepository.delete(exercise);
        }
    }
}
