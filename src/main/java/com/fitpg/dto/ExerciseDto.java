package com.fitpg.dto;

import com.fitpg.validation.group.OnCreate;
import com.fitpg.validation.group.OnExercise;
import com.fitpg.validation.group.OnUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.groups.ConvertGroup;
import lombok.Data;

import java.util.List;

/**
 * Data transfer object for Exercise entity.
 */
@Data
public class ExerciseDto {

    @Null(message = "{exercise.id.null}", groups = OnCreate.class)
    @NotNull(message = "{exercise.id.notNull}", groups = OnUpdate.class)
    private Long id;

    @Valid
    @ConvertGroup(from = OnUpdate.class, to = OnExercise.class)
    @ConvertGroup(from = OnCreate.class, to = OnExercise.class)
    private ExerciseInfoDto exerciseInfo;

    @Valid
    private List<ExerciseSetDto> exerciseSets;

    private String note;
}
