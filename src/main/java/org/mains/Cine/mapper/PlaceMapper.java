package org.mains.Cine.mapper;


import org.mains.Cine.domain.Place;
import org.mains.Cine.dto.PlaceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface PlaceMapper extends EntityMapper<PlaceDto, Place>{


}
