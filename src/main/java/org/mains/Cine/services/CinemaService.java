package org.mains.Cine.services;

import java.util.List;

import org.mains.Cine.dto.CinemaDto;
import org.mains.Cine.exceptions.DuplicateNameException;
import org.mains.Cine.exceptions.EmptyNameException;
import org.mains.Cine.exceptions.EntityNotDeletedException;
import org.mains.Cine.exceptions.EntityNotFoundException;


public interface CinemaService {
	 List<CinemaDto> findAllCinemas();
	 CinemaDto getOne(String id) throws EntityNotFoundException;
	 CinemaDto save(CinemaDto cinemaDto) throws EmptyNameException, DuplicateNameException;
	 void delete(String id) throws EntityNotDeletedException;
	 CinemaDto update(CinemaDto cinemaDto, String id) throws Exception;
}
