package org.mains.Cine.services.impl;

import java.util.List;
import java.util.Optional;

import org.mains.Cine.domain.Ville;
import org.mains.Cine.dto.VilleDto;
import org.mains.Cine.exceptions.DuplicateNameException;
import org.mains.Cine.exceptions.EmptyNameException;
import org.mains.Cine.exceptions.EntityNotDeletedException;
import org.mains.Cine.exceptions.EntityNotFoundException;
import org.mains.Cine.mapper.VilleMapper;
import org.mains.Cine.repositories.VilleRepository;
import org.mains.Cine.services.VilleService;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class VilleServiceImpl implements VilleService{
	private final VilleRepository villeRepository;
	private final VilleMapper villeMapper;

	@Override
	public List<VilleDto> findAllVilles() {
		List<Ville> listVilles= villeRepository.findAll();
		return villeMapper.toDto(listVilles);
	}

	@Override
	public VilleDto getOne(String id) throws EntityNotFoundException {
		Optional<Ville> ville=villeRepository.findById(id);
		if(ville.isPresent()) {
			return villeMapper.toDto(ville.get());
		} else {
			throw new EntityNotFoundException("Ville", id);
		}
	}

	@Override
	public VilleDto save(VilleDto villeDto) throws EmptyNameException, DuplicateNameException {
		Ville ville = villeMapper.toEntity(villeDto);

	    // Logique métier
	    validateVilleName(ville.getName());
	    checkDoublonVille(ville);

	    Ville savedVille = villeRepository.save(ville);

	    return villeMapper.toDto(savedVille);
	}

	private void validateVilleName(String villeName) throws EmptyNameException {
	    if (villeName == null || villeName.isEmpty()) {
	        throw new EmptyNameException(villeName);
	    }
	}

	private void checkDoublonVille(Ville ville) throws DuplicateNameException {
	    // Vérifier si une ville avec le même nom existe déjà
	    Optional<Ville> existingVille = villeRepository.findByNameIgnoreCase(ville.getName());

	    if (existingVille.isPresent()) {
	        throw new DuplicateNameException(ville.toString());

	    }

	}


	@Override
	public void delete(String id) throws EntityNotDeletedException {
		Optional<Ville> ville=villeRepository.findById(id);
		if(ville.isPresent()) {
			villeRepository.delete(ville.get());
		} else {
			throw new EntityNotDeletedException("Ville", id);
		}
	}


	@Override
	public VilleDto update(VilleDto villeDto, String id) throws EntityNotFoundException, EmptyNameException, DuplicateNameException {

		Ville ville = villeRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Ville", id));

	    // Logique métier
	    validateVilleName(villeDto.getName());
	    checkDoublonVille(villeMapper.toEntity(villeDto));

	    Ville updatedVille = villeMapper.toEntity(villeDto);
	    updatedVille.setId(ville.getId());
	    updatedVille.setName(ville.getName());

	    updatedVille = villeRepository.save(updatedVille);
	    return villeMapper.toDto(updatedVille);
	}



}
