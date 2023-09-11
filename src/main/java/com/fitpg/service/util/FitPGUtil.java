package com.fitpg.service.util;

import com.fitpg.dto.ExerciseInfoDto;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FitPGUtil {
    public static Map<ExerciseInfoDto, Boolean> mapExerciseToShow(List<ExerciseInfoDto> exerciseInfos,
                                                                  Set<String> muscleGroups) {
        return exerciseInfos.stream().collect(Collectors.toMap(e -> e,
                e -> e.getMuscleGroups().stream().anyMatch(muscleGroups::contains)));
    }
}
