package com.fitpg.dto;

import com.fitpg.validation.group.OnCreate;
import com.fitpg.validation.group.OnExercise;
import com.fitpg.validation.group.OnUpdate;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

import java.util.Set;

/**
 * Data transfer object for ExerciseInfo entity.
 */
@Data
public class ExerciseInfoDto {

    @Null(message = "{exerciseInfo.id.null}", groups = OnCreate.class)
    @NotNull(message = "{exerciseInfo.id.notNull}", groups = OnUpdate.class)
    @NotNull(message = "{exercise.exerciseInfo.id.notNull}", groups = OnExercise.class)
    private Long id;

    @NotNull(message = "{exerciseInfo.name.notNull}", groups = OnCreate.class)
    private String name;

    @NotEmpty(message = "{exerciseInfo.muscleGroups.notEmpty}", groups = OnCreate.class)
    private Set<String> muscleGroups;
}
