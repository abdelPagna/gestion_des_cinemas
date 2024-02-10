package org.mains.Cine.services;

import java.util.List;

import org.mains.Cine.dto.FilmDto;
import org.mains.Cine.exceptions.DuplicateNameException;
import org.mains.Cine.exceptions.EmptyNameException;
import org.mains.Cine.exceptions.EntityNotDeletedException;
import org.mains.Cine.exceptions.EntityNotFoundException;


public interface FilmService {
	 List<FilmDto> findAllFilms();
	 FilmDto getOne(String id)throws EntityNotFoundException;
	 FilmDto save(FilmDto filmDto)throws EmptyNameException, DuplicateNameException;
	 void delete(String id) throws EntityNotDeletedException;
	 FilmDto update(FilmDto filmDto, String id) throws EntityNotFoundException, EmptyNameException, DuplicateNameException;
}
