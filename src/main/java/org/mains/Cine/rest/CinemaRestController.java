package org.mains.Cine.rest;

import java.util.HashMap;
import java.util.Map;

import org.mains.Cine.dto.CinemaDto;
import org.mains.Cine.exceptions.DuplicateNameException;
import org.mains.Cine.exceptions.EmptyNameException;
import org.mains.Cine.exceptions.EntityNotDeletedException;
import org.mains.Cine.exceptions.EntityNotFoundException;
import org.mains.Cine.services.CinemaService;
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
@RequestMapping("/api/v1/cinemas")
@AllArgsConstructor
@Slf4j
public class CinemaRestController {
	//private CinemaRepository cinemaRepository;
	private final CinemaService cinemaService;

	@GetMapping("")
	public ResponseEntity<?> cinemas(){
		log.info("Requete Rest pour rétourner la liste des cinémas");
		Map<String, Object> map=new HashMap<>();
		map.put("cinemas", cinemaService.findAllCinemas());
		return ResponseEntity.ok(map);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCinemaById(@PathVariable(name = "id") String id) {
		log.info("Requete Rest pour rétourner un cinéma avec Id: {}", id);
		Map<String, Object> map=new HashMap<>();
		try {
			map.put("cinema", cinemaService.getOne(id));
		} catch (EntityNotFoundException e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		}
		return ResponseEntity.ok(map);
	}

	@PostMapping("")
	public ResponseEntity<?> saveCinema(@RequestBody CinemaDto cinemaDto) {
		log.info("Requete Rest pour enregister un cinéma");
		Map<String, Object> map=new HashMap<>();
		try {
			map.put("cinema", cinemaService.save(cinemaDto));
		} catch (EmptyNameException e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		} catch (DuplicateNameException e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		}
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCinema(@PathVariable(name = "id") String id, @RequestBody CinemaDto cinemaDto) throws EntityNotFoundException {
		log.info("Requete Rest pour mettre à jour un cinéma");
		Map<String, Object> map=new HashMap<>();
		try {
			map.put("cinema", cinemaService.update(cinemaDto,id));
		} catch (Exception e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		}
		return ResponseEntity.ok(map);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCinema(@PathVariable(name = "id") String id) {
		log.info("Requete Rest pour supprimer un cinéma avec Id: {}", id);
		try {
			cinemaService.delete(id);
		} catch (EntityNotDeletedException e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		}
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

}
