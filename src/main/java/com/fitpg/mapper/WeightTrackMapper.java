package com.fitpg.mapper;

import com.fitpg.dto.WeightTrackDto;
import com.fitpg.model.WeightTrack;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface WeightTrackMapper {

    WeightTrackDto mapWeightTrackDto(WeightTrack weightTrack);

    WeightTrack mapWeightTrack(WeightTrackDto weightTrackDto);

    void mapPresentFields(@MappingTarget WeightTrack toWeightTrack, WeightTrack fromWeightTrack);
}
