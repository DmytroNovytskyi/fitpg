package com.fitpg.dto;

import com.fitpg.validation.NullOrNotEmpty;
import com.fitpg.validation.group.OnCreate;
import com.fitpg.validation.group.OnUpdate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * Data transfer object for Workout entity.
 */
@Data
public class WorkoutDto {

    @Null(message = "{workout.id.null}", groups = OnCreate.class)
    @NotNull(message = "{workout.id.notNull}", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "{workout.date.notNull}", groups = OnCreate.class)
    private Date date;

    @NullOrNotEmpty(message = "{workout.exercises.notEmpty}", groups = OnUpdate.class)
    private Set<ExerciseDto> exercises;
}
