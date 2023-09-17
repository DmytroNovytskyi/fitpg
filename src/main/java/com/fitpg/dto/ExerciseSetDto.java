package com.fitpg.dto;

import com.fitpg.model.Unit;
import com.fitpg.validation.IsValueOfEnum;
import com.fitpg.validation.group.OnCreate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

/**
 * Data transfer object for ExerciseSet entity.
 */
@Data
public class ExerciseSetDto {

    @Null(message = "{exerciseSet.id.null}", groups = OnCreate.class)
    private Long id;

    @NotNull(message = "{exerciseSet.weight.notNull}")
    private Double weight;

    @IsValueOfEnum(fieldName = "{exerciseSet.unit.isValueOfEnum.fieldName}", enumClass = Unit.class)
    @NotNull(message = "{exerciseSet.unit.notNull}")
    private String unit;

    @NotNull(message = "{exerciseSet.repetitions.notNull}")
    private Integer repetitions;
}
