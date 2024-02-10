package org.mains.Cine.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.mains.Cine.domain.ProjectionFilm;
import org.mains.Cine.dto.ProjectionFilmDto;
import org.mains.Cine.exceptions.EntityNotDeletedException;
import org.mains.Cine.exceptions.EntityNotFoundException;
import org.mains.Cine.exceptions.InvalidProjectionDateException;
import org.mains.Cine.mapper.ProjectionFilmMapper;
import org.mains.Cine.repositories.ProjectionFilmRepository;
import org.mains.Cine.services.ProjectionFilmService;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ProjectionFilmServiceImpl implements ProjectionFilmService{
	private final ProjectionFilmRepository projectionFilmRepository;
	private final ProjectionFilmMapper projectionFilmMapper;

	@Override
	public List<ProjectionFilmDto> findAllProjectionFilms() {
		List<ProjectionFilm> listProjectionFilms= projectionFilmRepository.findAll();
		return projectionFilmMapper.toDto(listProjectionFilms);
	}

	@Override
	public ProjectionFilmDto getOne(String id) throws EntityNotFoundException {
		Optional<ProjectionFilm> projectionFilm=projectionFilmRepository.findById(id);
		if(projectionFilm.isPresent()) {
			return projectionFilmMapper.toDto(projectionFilm.get());
		} else {
			throw new EntityNotFoundException("Projection", id);
		}
	}

	@Override
	public ProjectionFilmDto save(ProjectionFilmDto projectionFilmDto) throws InvalidProjectionDateException {
		ProjectionFilm projectionFilm = projectionFilmMapper.toEntity(projectionFilmDto);

	    // Logique métier
		validateProjectionDate(projectionFilm.getDateProjection());
	    //checkDoublonProjectionFilm(projectionFilm);

	    ProjectionFilm savedProjectionFilm = projectionFilmRepository.save(projectionFilm);

	    return projectionFilmMapper.toDto(savedProjectionFilm);
	}
	public void validateProjectionDate(Date projectionDate) throws InvalidProjectionDateException {
        if (projectionDate == null || projectionDate.before(new Date())) {
            throw new InvalidProjectionDateException(projectionDate);
        }
    }
	/*
	 * private void validateProjectionFilmDate(Date projectionFilmDate) { if
	 * (projectionFilmDate == null) { throw new
	 * IllegalArgumentException("La date de la Projection ne peut pas être vide"); }
	 * }
	 */

	
	/*
	 * private void checkDoublonProjectionFilm(ProjectionFilm projectionFilm) throws
	 * DuplicateNameException { // Vérifier si une projection avec le même nom
	 * existe déjà Optional<ProjectionFilm> existingProjectionFilm =
	 * projectionFilmRepository.findByNameIgnoreCase(projectionFilm.
	 * getDateProjection());
	 * 
	 * if (existingProjectionFilm.isPresent()) { throw new
	 * DuplicateNameException(projectionFilm.toString());
	 * 
	 * }
	 * 
	 * }
	 */

	@Override
	public void delete(String id) throws EntityNotDeletedException {
		Optional<ProjectionFilm> projectionFilm=projectionFilmRepository.findById(id);
		if(projectionFilm.isPresent()) {
			projectionFilmRepository.delete(projectionFilm.get());
		} else {
			throw new EntityNotDeletedException("Projection", id);
		}
	}

	@Override
	public ProjectionFilmDto update(ProjectionFilmDto projectionFilmDto, String id) throws EntityNotFoundException, InvalidProjectionDateException {

		ProjectionFilm projectionFilm = projectionFilmRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Projection", id));

	    // Logique métier
		validateProjectionDate(projectionFilm.getDateProjection());
	    //checkDoublonProjectionFilm(projectionFilmMapper.toEntity(projectionFilmDto));

	    ProjectionFilm updatedProjectionFilm = projectionFilmMapper.toEntity(projectionFilmDto);
	    updatedProjectionFilm.setId(projectionFilm.getId());
	    updatedProjectionFilm.setDateProjection(projectionFilm.getDateProjection());
	    updatedProjectionFilm.setPrix(projectionFilm.getPrix());

	    updatedProjectionFilm = projectionFilmRepository.save(updatedProjectionFilm);
	    return projectionFilmMapper.toDto(updatedProjectionFilm);
	}



}
