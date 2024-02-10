package org.mains.Cine.rest;

import java.util.HashMap;
import java.util.Map;

import org.mains.Cine.dto.FilmDto;
import org.mains.Cine.exceptions.DuplicateNameException;
import org.mains.Cine.exceptions.EmptyNameException;
import org.mains.Cine.exceptions.EntityNotDeletedException;
import org.mains.Cine.exceptions.EntityNotFoundException;
import org.mains.Cine.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/films")
@AllArgsConstructor
@Slf4j
public class FilmRestController {
	@Autowired
	//private FilmRepository filmRepository;
	private final FilmService filmService;

	@GetMapping("/{id}")
	public ResponseEntity<?> getFilmById(@PathVariable(name = "id") String id) {
		log.info("Requete Rest pour rétourner un film avec Id: {}", id);
		Map<String, Object> map=new HashMap<>();
		try {
			map.put("film", filmService.getOne(id));
		} catch (EntityNotFoundException e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		}
		return ResponseEntity.ok(map);
	}

	@GetMapping("")
	public ResponseEntity<?> films(){
		log.info("Requete Rest pour rétourner la liste des films");
		Map<String, Object> map=new HashMap<>();
		map.put("film", filmService.findAllFilms());
		return ResponseEntity.ok(map);
	}

	@PostMapping("")
	public ResponseEntity<?> saveFilm(@RequestBody FilmDto filmDto) {
		log.info("Requete Rest pour sauvegarder un film");
		Map<String, Object> map=new HashMap<>();
		try {
			map.put("film", filmService.save(filmDto));
		} catch (EmptyNameException e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		} catch (DuplicateNameException e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		}
		return ResponseEntity.ok(map);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateFilm(@PathVariable(name = "id") String id, @RequestBody FilmDto filmDto) {
		log.info("Requete Rest pour mettre à jour un film");
		Map<String, Object> map=new HashMap<>();
		try {
			map.put("film", filmService.update(filmDto,id));
		} catch (EntityNotFoundException | EmptyNameException | DuplicateNameException e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		}
		return ResponseEntity.ok(map);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteFilm(@PathVariable(name = "id") String id) {
		log.info("Requete Rest pour sauvegarder un film");
		try {
			filmService.delete(id);
		} catch (EntityNotDeletedException e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		}
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}
