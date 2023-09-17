package com.fitpg.mapper;

import com.fitpg.dto.ExerciseInfoDto;
import com.fitpg.exception.RolesNotFoundException;
import com.fitpg.model.ExerciseInfo;
import com.fitpg.model.MuscleGroup;
import com.fitpg.repository.MuscleGroupRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public abstract class ExerciseInfoMapper {

    @Autowired
    protected MuscleGroupRepository muscleGroupRepository;

    @Mapping(target = "muscleGroups", qualifiedByName = "muscleGroupsToStrings")
    public abstract ExerciseInfoDto mapExerciseInfoDto(ExerciseInfo exerciseInfo);

    @Mapping(target = "muscleGroups", qualifiedByName = "stringsToMuscleGroups")
    public abstract ExerciseInfo mapExerciseInfo(ExerciseInfoDto exerciseInfoDto);

    public abstract void mapPresentFields(@MappingTarget ExerciseInfo toExerciseInfo, ExerciseInfo fromExerciseInfo);

    @Named("muscleGroupsToStrings")
    protected Set<String> muscleGroupsToStrings(Set<MuscleGroup> roles) {
        return roles.stream().map(MuscleGroup::getName).collect(Collectors.toSet());
    }

    @Named("stringsToMuscleGroups")
    protected Set<MuscleGroup> stringsToMuscleGroups(Set<String> muscleGroups) {
        if (muscleGroups != null) {
            Set<MuscleGroup> muscleGroupsEntities = muscleGroupRepository.findAllByNameIn(muscleGroups);
            if (muscleGroups.size() != muscleGroupsEntities.size()) {
                Set<String> acceptedRoles = new HashSet<>(muscleGroups);
                Set<String> returnedRoles = muscleGroupsEntities.stream()
                        .map(MuscleGroup::getName).collect(Collectors.toSet());
                acceptedRoles.removeAll(returnedRoles);
                throw new RolesNotFoundException(acceptedRoles);
            }
            return muscleGroupsEntities;
        }
        return null;
    }
}
