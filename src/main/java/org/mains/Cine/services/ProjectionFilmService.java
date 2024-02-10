package org.mains.Cine.services;

import java.util.List;

import org.mains.Cine.dto.ProjectionFilmDto;
import org.mains.Cine.exceptions.EmptyNameException;
import org.mains.Cine.exceptions.EntityNotDeletedException;
import org.mains.Cine.exceptions.EntityNotFoundException;
import org.mains.Cine.exceptions.InvalidProjectionDateException;


public interface ProjectionFilmService {
	 List<ProjectionFilmDto> findAllProjectionFilms();
	 ProjectionFilmDto getOne(String id) throws EntityNotFoundException;
	 ProjectionFilmDto save(ProjectionFilmDto projectionFilmDto) throws EmptyNameException, InvalidProjectionDateException;
	 void delete(String id) throws EntityNotDeletedException;
	 ProjectionFilmDto update(ProjectionFilmDto projectionFilmDto, String id) throws EntityNotFoundException, EmptyNameException, InvalidProjectionDateException;
}
