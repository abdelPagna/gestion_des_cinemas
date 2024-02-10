package org.mains.Cine.services;

import java.util.List;

import org.mains.Cine.dto.CategorieDto;
import org.mains.Cine.exceptions.DuplicateNameException;
import org.mains.Cine.exceptions.EmptyNameException;
import org.mains.Cine.exceptions.EntityNotDeletedException;
import org.mains.Cine.exceptions.EntityNotFoundException;


public interface CategorieService {
	 List<CategorieDto> findAllCategories();
	 CategorieDto getOne(String id) throws EntityNotFoundException;
	 CategorieDto save(CategorieDto categorieDto) throws EmptyNameException, DuplicateNameException;
	 void delete(String id) throws EntityNotDeletedException;
	 CategorieDto update(CategorieDto categorieDto, String id) throws EntityNotFoundException, EmptyNameException, DuplicateNameException;
}
