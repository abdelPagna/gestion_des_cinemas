package org.mains.Cine.services.impl;

import java.util.List;
import java.util.Optional;

import org.mains.Cine.domain.Film;
import org.mains.Cine.dto.FilmDto;
import org.mains.Cine.exceptions.DuplicateNameException;
import org.mains.Cine.exceptions.EmptyNameException;
import org.mains.Cine.exceptions.EntityNotDeletedException;
import org.mains.Cine.exceptions.EntityNotFoundException;
import org.mains.Cine.mapper.FilmMapper;
import org.mains.Cine.repositories.FilmRepository;
import org.mains.Cine.services.FilmService;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class FilmServiceImpl implements FilmService{
	private final FilmRepository filmRepository;
	private final FilmMapper filmMapper;

	@Override
	public List<FilmDto> findAllFilms() {
		List<Film> listFilms= filmRepository.findAll();
		return filmMapper.toDto(listFilms);
	}

	@Override
	public FilmDto getOne(String id) throws EntityNotFoundException {
		Optional<Film> film=filmRepository.findById(id);
		if(film.isPresent()) {
			return filmMapper.toDto(film.get());
		} else {
			throw new EntityNotFoundException("Films", id);
		}
	}

	@Override
	public FilmDto save(FilmDto filmDto) throws EmptyNameException, DuplicateNameException {
	    Film film = filmMapper.toEntity(filmDto);

	    // Logique métier
	    validateFilmTitre(film.getTitre());
	    checkDoublonFilm(film);

	    Film savedFilm = filmRepository.save(film);

	    return filmMapper.toDto(savedFilm);
	}

	private void validateFilmTitre(String filmTitre) throws EmptyNameException {
	    if (filmTitre == null || filmTitre.isEmpty()) {
	        throw new EmptyNameException(filmTitre);
	    }
	}

	private void checkDoublonFilm(Film film) throws DuplicateNameException {
	    // Vérifier si une catégorie avec le même nom existe déjà
	    Optional<Film> existingFilm = filmRepository.findByTitreIgnoreCase(film.getTitre());

	    if (existingFilm.isPresent()) {
	        throw new DuplicateNameException(film.toString());

	    }

	}

	@Override
	public void delete(String id) throws EntityNotDeletedException {
		Optional<Film> film=filmRepository.findById(id);
		if(film.isPresent()) {
			filmRepository.delete(film.get());
		} else {
			throw new EntityNotDeletedException("Film", id);
		}
	}

	@Override
	public FilmDto update(FilmDto filmDto, String id) throws EmptyNameException, DuplicateNameException, EntityNotFoundException {
	    // Convertir le DTO en entité en utilisant le mapper
	    Film film = filmRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Films", id));

	    // Logique métier
	    validateFilmTitre(filmDto.getTitre());
	    checkDoublonFilm(filmMapper.toEntity(filmDto));

	    Film updatedFilm = filmMapper.toEntity(filmDto);
	    updatedFilm.setId(film.getId());
	    updatedFilm.setTitre(film.getTitre());
	    updatedFilm.setDuree(film.getDuree());
	    updatedFilm.setRealisateur(film.getRealisateur());
	    updatedFilm.setDescription(film.getDescription());
	    updatedFilm.setPhoto(film.getPhoto());
	    updatedFilm.setDateSortie(film.getDateSortie());

	    updatedFilm = filmRepository.save(updatedFilm);
	    return filmMapper.toDto(updatedFilm);
	}



}
