package com.fitpg.dto;

import com.fitpg.validation.group.OnCreate;
import com.fitpg.validation.group.OnUpdate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Data transfer object for WeightTrack entity.
 */
@Data
public class WeightTrackDto {

    @Null(message = "{weightTrack.id.null}", groups = OnCreate.class)
    @NotNull(message = "{weightTrack.id.notNull}", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "{weightTrack.weight.notNull}", groups = OnCreate.class)
    private Double weight;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "{weightTrack.date.notNull}", groups = OnCreate.class)
    private Date date;
}
