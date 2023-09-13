package com.fitpg.mapper;

import com.fitpg.dto.ExerciseDto;
import com.fitpg.exception.ExerciseInfoNotFoundException;
import com.fitpg.model.Exercise;
import com.fitpg.model.ExerciseInfo;
import com.fitpg.model.ExerciseSet;
import com.fitpg.repository.ExerciseInfoRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = {ExerciseInfoMapper.class, ExerciseSetMapper.class}, componentModel = "spring")
public abstract class ExerciseMapper {

    @Autowired
    protected ExerciseInfoRepository exerciseInfoRepository;

    public abstract ExerciseDto mapExerciseDto(Exercise exercise);

    public abstract Exercise mapExercise(ExerciseDto exerciseDto);

    @Mapping(target = "exerciseInfo", qualifiedByName = "exerciseInfoIdToExerciseInfo")
    public abstract void mapPresentFields(@MappingTarget Exercise toExercise, Exercise fromExercise);

    @Named("exerciseInfoIdToExerciseInfo")
    protected ExerciseInfo muscleGroupsToStrings(ExerciseInfo exerciseInfo) {
        return exerciseInfoRepository.findById(exerciseInfo.getId()).orElseThrow(ExerciseInfoNotFoundException::new);
    }

    @AfterMapping
    protected void linkExerciseSets(@MappingTarget Exercise exercise) {
        List<ExerciseSet> exerciseSets = exercise.getExerciseSets();
        if (exerciseSets != null) {
            exerciseSets.forEach(s -> s.setExercise(exercise));
        } else {
            exercise.setExerciseSets(new ArrayList<>());
        }
    }
}
