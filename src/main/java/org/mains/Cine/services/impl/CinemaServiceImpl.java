package org.mains.Cine.services.impl;

import java.util.List;
import java.util.Optional;

import org.mains.Cine.domain.Cinema;
import org.mains.Cine.dto.CinemaDto;
import org.mains.Cine.exceptions.DuplicateNameException;
import org.mains.Cine.exceptions.EmptyNameException;
import org.mains.Cine.exceptions.EntityNotDeletedException;
import org.mains.Cine.exceptions.EntityNotFoundException;
import org.mains.Cine.mapper.CinemaMapper;
import org.mains.Cine.repositories.CinemaRepository;
import org.mains.Cine.services.CinemaService;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CinemaServiceImpl implements CinemaService{
	private final CinemaRepository cinemaRepository;
	private final CinemaMapper cinemaMapper;

	@Override
	public List<CinemaDto> findAllCinemas() {
		List<Cinema> listCinemas= cinemaRepository.findAll();
		return cinemaMapper.toDto(listCinemas);
	}

	@Override
	public CinemaDto getOne(String id) throws EntityNotFoundException {
		Optional<Cinema> cinema=cinemaRepository.findById(id);
		if(cinema.isPresent()) {
			return cinemaMapper.toDto(cinema.get());
		} else {
			throw new EntityNotFoundException("Cinema", id);
		}
	}

	@Override
	public CinemaDto save(CinemaDto cinemaDto) throws EmptyNameException, DuplicateNameException {
		Cinema cinema = cinemaMapper.toEntity(cinemaDto);

		// Logique métier
		validateCinemaName(cinema.getName());
		checkDoublonCinema(cinema);


	    Cinema savedCinema = cinemaRepository.save(cinema);

	    return cinemaMapper.toDto(savedCinema);
	}

	@Override
	public void delete(String id) throws EntityNotDeletedException {
		Optional<Cinema> cinema=cinemaRepository.findById(id);
		if(cinema.isPresent()) {
			cinemaRepository.delete(cinema.get());
		} else {
			throw new EntityNotDeletedException("Cinema", id);
		}
	}


	@Override
	public CinemaDto update(CinemaDto cinemaDto, String id) throws EntityNotFoundException, EmptyNameException, DuplicateNameException  {

		Cinema cinema = cinemaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cinema", id));

	    // Logique métier
	    validateCinemaName(cinemaDto.getName());
	    checkDoublonCinema(cinemaMapper.toEntity(cinemaDto));
		
	    Cinema updatedCinema = cinemaMapper.toEntity(cinemaDto);
	    updatedCinema.setId(cinema.getId());
	    updatedCinema.setName(cinema.getName());
	    updatedCinema.setLongitude(cinema.getLongitude());
	    updatedCinema.setLatitude(cinema.getLatitude());
	    updatedCinema.setAltitude(cinema.getAltitude());
	    updatedCinema.setNombreSalle(cinema.getNombreSalle());

	    updatedCinema = cinemaRepository.save(updatedCinema);
	    return cinemaMapper.toDto(updatedCinema);
	}
	
	private void validateCinemaName(String cinemaName) throws EmptyNameException {
	    if (cinemaName == null || cinemaName.isEmpty()) {
	    	throw new EmptyNameException(cinemaName);
	    	//throw new IllegalArgumentException("Le nom du cinema ne peut pas être vide");
	    }
	}

	private void checkDoublonCinema(Cinema cinema) throws DuplicateNameException {
	    // Vérifier si un cinema avec le même nom existe déjà
	    Optional<Cinema> existingCinema = cinemaRepository.findByNameIgnoreCase(cinema.getName());

	    if (existingCinema.isPresent()) {
	    	throw new DuplicateNameException(cinema.toString());
	    	//throw new IllegalStateException("Un cinema avec le même nom existe déjà");

	    }

	}

}
