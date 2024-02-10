package org.mains.Cine.services.impl;

import java.util.List;
import java.util.Optional;

import org.mains.Cine.domain.Categorie;
import org.mains.Cine.dto.CategorieDto;
import org.mains.Cine.exceptions.DuplicateNameException;
import org.mains.Cine.exceptions.EmptyNameException;
import org.mains.Cine.exceptions.EntityNotDeletedException;
import org.mains.Cine.exceptions.EntityNotFoundException;
import org.mains.Cine.mapper.CategorieMapper;
import org.mains.Cine.repositories.CategorieRepository;
import org.mains.Cine.services.CategorieService;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CategorieServiceImpl implements CategorieService{
	private final CategorieRepository categorieRepository;
	private final CategorieMapper categorieMapper;

	@Override
	public List<CategorieDto> findAllCategories() {
		List<Categorie> listCategories= categorieRepository.findAll();
		return categorieMapper.toDto(listCategories);
	}

	@Override
	public CategorieDto getOne(String id) throws EntityNotFoundException {
		Optional<Categorie> categorie=categorieRepository.findById(id);
		if(categorie.isPresent()) {
			return categorieMapper.toDto(categorie.get());
		} else {
			throw new EntityNotFoundException("Categorie", id);
		}
	}

	@Override
	public CategorieDto save(CategorieDto categorieDto) throws EmptyNameException, DuplicateNameException {
	    Categorie categorie = categorieMapper.toEntity(categorieDto);

	    // Logique métier
	    validateCategorieName(categorie.getName());
	    checkDoublonCategorie(categorie);

	    Categorie savedCategorie = categorieRepository.save(categorie);

	    return categorieMapper.toDto(savedCategorie);
	}


	@Override
	public void delete(String id) throws EntityNotDeletedException {
	    Optional<Categorie> categorie = categorieRepository.findById(id);
	    if (categorie.isPresent()) {
	        categorieRepository.delete(categorie.get());
	    } else {
	        throw new EntityNotDeletedException("Categorie", id);
	    }
	}

	@Override
	public CategorieDto update(CategorieDto categorieDto, String id) throws EntityNotFoundException, EmptyNameException, DuplicateNameException {

	    Categorie categorie = categorieRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Categoerie", id));
	    
	   
            validateCategorieName(categorieDto.getName());
            checkDoublonCategorie(categorie);
       


	    Categorie updatedCategorie = categorieMapper.toEntity(categorieDto);
	    updatedCategorie.setId(categorie.getId());
	    updatedCategorie.setName(categorie.getName());

	    updatedCategorie = categorieRepository.save(updatedCategorie);
	    return categorieMapper.toDto(updatedCategorie);
	}

	private void validateCategorieName(String categorieName) throws EmptyNameException {
	    if (categorieName == null || categorieName.isEmpty()) {
	    	throw new EmptyNameException(categorieName);
	    }
	}

	private void checkDoublonCategorie(Categorie categorie) throws DuplicateNameException {
	    // Vérifier si un categorie avec le même nom existe déjà
	    Optional<Categorie> existingCategorie = categorieRepository.findByNameIgnoreCase(categorie.getName());

	    if (existingCategorie.isPresent()) {
	    	throw new DuplicateNameException(categorie.toString());
	    	//throw new IllegalStateException("Une categorie avec le même nom existe déjà");

	    }

	}


}
