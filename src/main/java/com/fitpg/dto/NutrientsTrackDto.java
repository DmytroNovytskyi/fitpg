package com.fitpg.dto;

import com.fitpg.validation.group.OnCreate;
import com.fitpg.validation.group.OnUpdate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Data transfer object for NutrientsTrack entity.
 */
@Data
public class NutrientsTrackDto {

    @Null(message = "{nutrientsTrack.id.null}", groups = OnCreate.class)
    @NotNull(message = "{nutrientsTrack.id.notNull}", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "{nutrientsTrack.fats.notNull}", groups = OnCreate.class)
    private Integer fats;

    @NotNull(message = "{nutrientsTrack.carbohydrates.notNull}", groups = OnCreate.class)
    private Integer carbohydrates;

    @NotNull(message = "{nutrientsTrack.protein.notNull}", groups = OnCreate.class)
    private Integer protein;

    @NotNull(message = "{nutrientsTrack.calories.notNull}", groups = OnCreate.class)
    private Integer calories;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "{nutrientsTrack.date.notNull}", groups = OnCreate.class)
    private Date date;
}
