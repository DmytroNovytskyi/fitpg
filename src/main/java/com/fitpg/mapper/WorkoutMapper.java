package com.fitpg.mapper;

import com.fitpg.dto.WorkoutDto;
import com.fitpg.model.Workout;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Objects;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = ExerciseMapper.class,
        componentModel = "spring")
public abstract class WorkoutMapper {

    public abstract WorkoutDto mapWorkoutDto(Workout workout);

    public abstract Workout mapWorkout(WorkoutDto workoutDto);

    public abstract void mapPresentFields(@MappingTarget Workout toWorkout, Workout fromWorkout);

    @AfterMapping
    protected void removeNullFromExercises(@MappingTarget WorkoutDto workoutDto) {
        workoutDto.getExercises().removeIf(Objects::isNull);
    }
}
