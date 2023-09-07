package com.fitpg.dto;

import com.fitpg.model.Unit;
import com.fitpg.validation.IsValueOfEnum;
import com.fitpg.validation.group.OnCreate;
import com.fitpg.validation.group.OnUpdate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

/**
 * Data transfer object for ExerciseSet entity.
 */
@Data
public class ExerciseSetDto {

    @Null(message = "{exerciseSet.id.null}", groups = OnCreate.class)
    @NotNull(message = "{exerciseSet.id.notNull}", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "{exerciseSet.weight.notNull}", groups = OnCreate.class)
    private Double weight;

    @IsValueOfEnum(fieldName = "{exerciseSet.unit.isValueOfEnum.fieldName}", enumClass = Unit.class)
    @NotNull(message = "{exerciseSet.unit.notNull}", groups = OnCreate.class)
    private String unit;

    @NotNull(message = "{exerciseSet.repetitions.notNull}", groups = OnCreate.class)
    private Integer repetitions;
}
