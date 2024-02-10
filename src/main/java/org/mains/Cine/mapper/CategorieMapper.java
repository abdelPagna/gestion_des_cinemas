package org.mains.Cine.mapper;

import org.mains.Cine.domain.Categorie;
import org.mains.Cine.dto.CategorieDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface CategorieMapper extends EntityMapper<CategorieDto, Categorie>{


}
