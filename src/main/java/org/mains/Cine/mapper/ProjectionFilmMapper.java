package org.mains.Cine.mapper;

import org.mains.Cine.domain.ProjectionFilm;
import org.mains.Cine.dto.ProjectionFilmDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ProjectionFilmMapper extends EntityMapper<ProjectionFilmDto, ProjectionFilm>{

}
