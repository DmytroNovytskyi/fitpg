package com.fitpg.mapper;

import com.fitpg.dto.MuscleGroupDto;
import com.fitpg.exception.ExerciseInfosNotFoundException;
import com.fitpg.model.ExerciseInfo;
import com.fitpg.model.MuscleGroup;
import com.fitpg.repository.ExerciseInfoRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public abstract class MuscleGroupMapper {

    @Autowired
    protected ExerciseInfoRepository exerciseInfoRepository;

    @Mapping(target = "exercises", qualifiedByName = "exercisesToStrings")
    public abstract MuscleGroupDto mapMuscleGroupDto(MuscleGroup muscleGroup);

    @Mapping(target = "exercises", qualifiedByName = "stringsToExercises")
    public abstract MuscleGroup mapMuscleGroup(MuscleGroupDto muscleGroupDto);

    public abstract void mapPresentFields(@MappingTarget MuscleGroup toMuscleGroup, MuscleGroup fromMuscleGroup);

    @Named("exercisesToStrings")
    protected Set<String> exercisesToStrings(Set<ExerciseInfo> exercises) {
        return exercises.stream().map(ExerciseInfo::getName).collect(Collectors.toSet());
    }

    @Named("stringsToExercises")
    protected Set<ExerciseInfo> stringsToExercises(Set<String> exercises) {
        if (exercises != null) {
            Set<ExerciseInfo> exerciseInfos = exerciseInfoRepository.findAllByNameIn(exercises);
            if (exercises.size() != exerciseInfos.size()) {
                Set<String> acceptedExercises = new HashSet<>(exercises);
                Set<String> returnedExercises = exerciseInfos.stream()
                        .map(ExerciseInfo::getName).collect(Collectors.toSet());
                acceptedExercises.removeAll(returnedExercises);
                throw new ExerciseInfosNotFoundException(acceptedExercises);
            }
            return exerciseInfos;
        }
        return null;
    }
}
