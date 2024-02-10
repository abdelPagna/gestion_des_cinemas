package org.mains.Cine.services.impl;

import java.util.List;
import java.util.Optional;

import org.mains.Cine.domain.Salle;
import org.mains.Cine.dto.SalleDto;
import org.mains.Cine.exceptions.DuplicateNameException;
import org.mains.Cine.exceptions.EmptyNameException;
import org.mains.Cine.exceptions.EntityNotDeletedException;
import org.mains.Cine.exceptions.EntityNotFoundException;
import org.mains.Cine.mapper.SalleMapper;
import org.mains.Cine.repositories.SalleRepository;
import org.mains.Cine.services.SalleService;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class SalleServiceImpl implements SalleService{
	private final SalleRepository salleRepository;
	private final SalleMapper salleMapper;

	@Override
	public List<SalleDto> findAllSalles() {
		List<Salle> listSalles= salleRepository.findAll();
		return salleMapper.toDto(listSalles);
	}

	@Override
	public SalleDto getOne(String id) throws EntityNotFoundException {
		Optional<Salle> salle=salleRepository.findById(id);
		if(salle.isPresent()) {
			return salleMapper.toDto(salle.get());
		} else {
			throw new EntityNotFoundException("Salle", id);
		}
	}

	@Override
	public SalleDto save(SalleDto salleDto) throws EmptyNameException, DuplicateNameException {
		Salle salle = salleMapper.toEntity(salleDto);

	    // Logique métier
	    validateSalleName(salle.getName());
	    checkDoublonSalle(salle);

	    Salle savedSalle = salleRepository.save(salle);

	    return salleMapper.toDto(savedSalle);
	}

	private void validateSalleName(String salleName) throws EmptyNameException {
	    if (salleName == null || salleName.isEmpty()) {
	        throw new EmptyNameException(salleName);
	    }
	}

	private void checkDoublonSalle(Salle salle) throws DuplicateNameException {
	    // Vérifier si une salle avec le même nom existe déjà
	    Optional<Salle> existingSalle = salleRepository.findByNameIgnoreCase(salle.getName());

	    if (existingSalle.isPresent()) {
	        throw new DuplicateNameException(salle.toString());

	    }

	}

	@Override
	public void delete(String id) throws EntityNotDeletedException {
		Optional<Salle> salle=salleRepository.findById(id);
		if(salle.isPresent()) {
			salleRepository.delete(salle.get());
		} else {
			throw new EntityNotDeletedException("Salle", id);
		}
	}


	@Override
	public SalleDto update(SalleDto salleDto, String id) throws EntityNotFoundException, EmptyNameException, DuplicateNameException {

		Salle salle = salleRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Salle", id));

	    // Logique métier
	    validateSalleName(salleDto.getName());
	    checkDoublonSalle(salleMapper.toEntity(salleDto));

	    Salle updatedSalle = salleMapper.toEntity(salleDto);
	    updatedSalle.setId(salle.getId());
	    updatedSalle.setName(salle.getName());
	    updatedSalle.setNombrePlace(salle.getNombrePlace());

	    updatedSalle = salleRepository.save(updatedSalle);
	    return salleMapper.toDto(updatedSalle);
	}



}
