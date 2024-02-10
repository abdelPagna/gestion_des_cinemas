package org.mains.Cine.services.impl;

import java.util.List;
import java.util.Optional;

import org.mains.Cine.domain.Place;
import org.mains.Cine.dto.PlaceDto;
import org.mains.Cine.exceptions.EntityNotDeletedException;
import org.mains.Cine.exceptions.EntityNotFoundException;
import org.mains.Cine.mapper.PlaceMapper;
import org.mains.Cine.repositories.PlaceRepository;
import org.mains.Cine.services.PlaceService;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class PlaceServiceImpl implements PlaceService{
	private final PlaceRepository placeRepository;
	private final PlaceMapper placeMapper;

	@Override
	public List<PlaceDto> findAllPlaces() {
		List<Place> listPlaces= placeRepository.findAll();
		return placeMapper.toDto(listPlaces);
	}

	@Override
	public PlaceDto getOne(String id) throws EntityNotFoundException {
		Optional<Place> place=placeRepository.findById(id);
		if(place.isPresent()) {
			return placeMapper.toDto(place.get());
		} else {
			throw new EntityNotFoundException("Place", id);
		}
	}

	@Override
	public PlaceDto save(PlaceDto placeDto) {
		Place place = placeMapper.toEntity(placeDto);

	    // Logique métier
	    validatePlaceNumero(place.getNumero());
	    //checkDoublonPlace(place);

	    Place savedPlace = placeRepository.save(place);

	    return placeMapper.toDto(savedPlace);
	}

	private void validatePlaceNumero(int placeNumero) {
	    if (placeNumero == 0) {
	        throw new IllegalArgumentException("Le numéro de la Place ne peut pas être vide");
	    }
	}


	@Override
	public void delete(String id) throws EntityNotDeletedException {
		Optional<Place> place=placeRepository.findById(id);
		if(place.isPresent()) {
			placeRepository.delete(place.get());
		} else {
			throw new EntityNotDeletedException("Place", id);
		}
	}

	@Override
	public PlaceDto update(PlaceDto placeDto, String id) throws EntityNotFoundException {

		Place place = placeRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Places", id));

	    // Logique métier
	    validatePlaceNumero(place.getNumero());
	    //checkDoublonPlace(placeMapper.toEntity(placeDto));

	    Place updatedPlace = placeMapper.toEntity(placeDto);
	    updatedPlace.setId(place.getId());
	    updatedPlace.setNumero(place.getNumero());
	    updatedPlace.setLongitude(place.getLongitude());
	    updatedPlace.setLatitude(place.getLatitude());
	    updatedPlace.setAltitude(place.getAltitude());

	    updatedPlace = placeRepository.save(updatedPlace);
	    return placeMapper.toDto(updatedPlace);
	}



}
