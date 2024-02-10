package org.mains.Cine.mapper;

import org.mains.Cine.domain.Salle;
import org.mains.Cine.dto.SalleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface SalleMapper extends EntityMapper<SalleDto, Salle>{


}

