package org.mains.Cine.services;

import java.util.List;

import org.mains.Cine.dto.VilleDto;
import org.mains.Cine.exceptions.DuplicateNameException;
import org.mains.Cine.exceptions.EmptyNameException;
import org.mains.Cine.exceptions.EntityNotDeletedException;
import org.mains.Cine.exceptions.EntityNotFoundException;


public interface VilleService {
	 List<VilleDto> findAllVilles();
	 VilleDto getOne(String id) throws EntityNotFoundException;
	 VilleDto save(VilleDto villeDto) throws EmptyNameException, DuplicateNameException;
	 void delete(String id) throws EntityNotDeletedException;
	 VilleDto update(VilleDto villeDto, String id) throws EntityNotFoundException, EmptyNameException, DuplicateNameException;
}
