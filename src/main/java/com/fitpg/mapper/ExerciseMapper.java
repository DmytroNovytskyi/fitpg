package com.fitpg.mapper;

import com.fitpg.dto.ExerciseDto;
import com.fitpg.model.Exercise;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {ExerciseInfoMapper.class, ExerciseSetMapper.class},
        componentModel = "spring")
public interface ExerciseMapper {

    ExerciseDto mapExerciseDto(Exercise exercise);

    Exercise mapExercise(ExerciseDto exerciseDto);

    void mapPresentFields(@MappingTarget Exercise toExercise, Exercise fromExercise);
}
