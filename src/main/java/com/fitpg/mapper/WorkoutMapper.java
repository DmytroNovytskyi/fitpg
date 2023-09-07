package com.fitpg.mapper;

import com.fitpg.dto.WorkoutDto;
import com.fitpg.model.Workout;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = ExerciseMapper.class,
        componentModel = "spring")
public interface WorkoutMapper {

    WorkoutDto mapWorkoutDto(Workout workout);

    Workout mapWorkout(WorkoutDto workoutDto);

    void mapPresentFields(@MappingTarget Workout toWorkout, Workout fromWorkout);
}
