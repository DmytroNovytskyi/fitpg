package com.fitpg.dto;

import com.fitpg.validation.group.OnCreate;
import com.fitpg.validation.group.OnUpdate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
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

    @NotNull(message = "{exercise.exerciseInfo.notNull}", groups = OnCreate.class)
    private ExerciseInfoDto exerciseInfo;

    @NotNull(message = "{exercise.exerciseSets.notNull}", groups = OnCreate.class)
    private List<ExerciseSetDto> exerciseSets;

    private String note;
}
