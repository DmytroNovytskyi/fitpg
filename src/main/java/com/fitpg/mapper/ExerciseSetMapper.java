package com.fitpg.mapper;

import com.fitpg.dto.ExerciseSetDto;
import com.fitpg.model.ExerciseSet;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface ExerciseSetMapper {

    ExerciseSetDto mapExerciseSetDto(ExerciseSet exerciseSet);

    ExerciseSet mapExerciseSet(ExerciseSetDto exerciseSetDto);

    void mapPresentFields(@MappingTarget ExerciseSet toExerciseSet, ExerciseSet fromExerciseSet);
}
