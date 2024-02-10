package org.mains.Cine.mapper;

import org.mains.Cine.domain.Cinema;
import org.mains.Cine.dto.CinemaDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface CinemaMapper extends EntityMapper<CinemaDto, Cinema>{


}