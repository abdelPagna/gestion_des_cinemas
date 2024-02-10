package org.mains.Cine.services;

import java.util.List;

import org.mains.Cine.dto.SalleDto;
import org.mains.Cine.exceptions.DuplicateNameException;
import org.mains.Cine.exceptions.EmptyNameException;
import org.mains.Cine.exceptions.EntityNotDeletedException;
import org.mains.Cine.exceptions.EntityNotFoundException;


public interface SalleService {
	 List<SalleDto> findAllSalles();
	 SalleDto getOne(String id) throws EntityNotFoundException;
	 SalleDto save(SalleDto salleDto) throws EmptyNameException, DuplicateNameException;
	 void delete(String id) throws EntityNotDeletedException;
	 SalleDto update(SalleDto salleDto, String id) throws EntityNotFoundException, EmptyNameException, DuplicateNameException;
}
