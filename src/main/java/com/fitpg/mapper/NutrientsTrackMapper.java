package com.fitpg.mapper;

import com.fitpg.dto.NutrientsTrackDto;
import com.fitpg.model.NutrientsTrack;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface NutrientsTrackMapper {

    NutrientsTrackDto mapNutrientsTrackDto(NutrientsTrack nutrientsTrack);

    NutrientsTrack mapNutrientsTrack(NutrientsTrackDto nutrientsTrackDto);

    void mapPresentFields(@MappingTarget NutrientsTrack toNutrientsTrack, NutrientsTrack fromNutrientsTrack);
}
