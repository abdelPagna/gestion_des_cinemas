package org.mains.Cine.services;

import java.util.List;

import org.mains.Cine.dto.PlaceDto;
import org.mains.Cine.exceptions.DuplicateNameException;
import org.mains.Cine.exceptions.EmptyNameException;
import org.mains.Cine.exceptions.EntityNotDeletedException;
import org.mains.Cine.exceptions.EntityNotFoundException;


public interface PlaceService {
	 List<PlaceDto> findAllPlaces();
	 PlaceDto getOne(String id) throws EntityNotFoundException;
	 PlaceDto save(PlaceDto placeDto) throws EmptyNameException, DuplicateNameException;
	 void delete(String id) throws EntityNotDeletedException;
	 PlaceDto update(PlaceDto placeDto, String id) throws EntityNotFoundException, EmptyNameException, DuplicateNameException;
}
