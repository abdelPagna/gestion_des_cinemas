package org.mains.Cine.mapper;


import org.mains.Cine.domain.Ville;
import org.mains.Cine.dto.VilleDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {})
public interface VilleMapper extends EntityMapper<VilleDto, Ville>{


}
