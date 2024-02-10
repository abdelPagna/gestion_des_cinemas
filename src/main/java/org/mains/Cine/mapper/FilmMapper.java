package org.mains.Cine.mapper;

import org.mains.Cine.domain.Film;
import org.mains.Cine.dto.FilmDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface FilmMapper extends EntityMapper<FilmDto, Film>{


}