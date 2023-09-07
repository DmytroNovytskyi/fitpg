package com.fitpg.dto;

import com.fitpg.validation.group.OnCreate;
import com.fitpg.validation.group.OnUpdate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

import java.util.Set;

/**
 * Data transfer object for MuscleGroup entity.
 */
@Data
public class MuscleGroupDto {

    @Null(message = "{muscleGroup.id.null}", groups = OnCreate.class)
    @NotNull(message = "{muscleGroup.id.notNull}", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "{muscleGroup.name.notNull}", groups = OnCreate.class)
    private String name;

    private Set<String> exercises;
}
