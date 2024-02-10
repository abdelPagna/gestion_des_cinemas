package org.mains.Cine.rest;

import java.util.HashMap;
import java.util.Map;

import org.mains.Cine.dto.ProjectionFilmDto;
import org.mains.Cine.exceptions.EmptyNameException;
import org.mains.Cine.exceptions.EntityNotDeletedException;
import org.mains.Cine.exceptions.EntityNotFoundException;
import org.mains.Cine.exceptions.InvalidProjectionDateException;
import org.mains.Cine.services.ProjectionFilmService;
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
@RequestMapping("/api/v1/projectionFilms")
@AllArgsConstructor
@Slf4j
public class ProjectionFilmRestController {
	@Autowired
	private final ProjectionFilmService projectionFilmService;

	@GetMapping("/{id}")
	public ResponseEntity<?> getProjectionFilmById(@PathVariable(name = "id") String id) {
		log.info("Requete Rest pour rétourner une projection avec Id: {}", id);
		Map<String, Object> map=new HashMap<>();
		try {
			map.put("projection", projectionFilmService.getOne(id));
		} catch (EntityNotFoundException e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		}
		return ResponseEntity.ok(map);
	}

	@GetMapping("")
	public ResponseEntity<?> projectionFilms(){
		log.info("Requete Rest pour rétourner la liste des projections");
		Map<String, Object> map=new HashMap<>();
		map.put("Projections", projectionFilmService.findAllProjectionFilms());
		return ResponseEntity.ok(map);
	}

	@PostMapping("")
	public ResponseEntity<?> saveProjectionFilm(@RequestBody ProjectionFilmDto projectionFilmDto) {
		log.info("Requete Rest pour enregister une projection");
		Map<String, Object> map=new HashMap<>();
		try {
			map.put("projection", projectionFilmService.save(projectionFilmDto));
		} catch (EmptyNameException | InvalidProjectionDateException e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		} 
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateProjectionFilm(@PathVariable(name = "id") String id, @RequestBody ProjectionFilmDto projectionFilmDto) {
		log.info("Requete Rest pour mettre à jour une projection");
		Map<String, Object> map=new HashMap<>();
		try {
			map.put("projection", projectionFilmService.update(projectionFilmDto,id));
		} catch (EntityNotFoundException | EmptyNameException | InvalidProjectionDateException e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		}
		return ResponseEntity.ok(map);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProjectionFilm(@PathVariable(name = "id") String id) {
		log.info("Requete Rest pour supprimer une projection avec Id: {}", id);
		try {
			projectionFilmService.delete(id);
		} catch (EntityNotDeletedException e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		}
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

}
