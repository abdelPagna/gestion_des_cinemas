package org.mains.Cine.rest;

import java.util.HashMap;
import java.util.Map;

import org.mains.Cine.dto.SalleDto;
import org.mains.Cine.exceptions.DuplicateNameException;
import org.mains.Cine.exceptions.EmptyNameException;
import org.mains.Cine.exceptions.EntityNotDeletedException;
import org.mains.Cine.exceptions.EntityNotFoundException;
import org.mains.Cine.services.SalleService;
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
@RequestMapping("/api/v1/salles")
@AllArgsConstructor
@Slf4j
public class SalleRestController {
	private SalleService salleService;

	@GetMapping("/{id}")
	public ResponseEntity<?> getSalleById(@PathVariable(name = "id") String id) {
		log.info("Requete Rest pour rétourner une salle avec Id: {}", id);
		Map<String, Object> map=new HashMap<>();
		try {
			map.put("salle", salleService.getOne(id));
		} catch (EntityNotFoundException e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		}
		return ResponseEntity.ok(map);
	}

	@GetMapping("")
	public ResponseEntity<?> salles(){
		log.info("Requete Rest pour rétourner la liste des salles");
		Map<String, Object> map=new HashMap<>();
		map.put("salles", salleService.findAllSalles());
		return ResponseEntity.ok(map);
	}

	@PostMapping("")
	public ResponseEntity<?> saveSalle(@RequestBody SalleDto salleDto) {
		log.info("Requete Rest pour enregister une salle");
		Map<String, Object> map=new HashMap<>();
		try {
			map.put("salle", salleService.save(salleDto));
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
	public ResponseEntity<?> updateSalle(@PathVariable(name = "id") String id, @RequestBody SalleDto salleDto) {
		log.info("Requete Rest pour mettre à jour une salle");
		Map<String, Object> map=new HashMap<>();
		try {
			map.put("salle", salleService.update(salleDto,id));
		} catch (EntityNotFoundException | EmptyNameException | DuplicateNameException e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		}
		return ResponseEntity.ok(map);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteSalle(@PathVariable(name = "id") String id) {
		log.info("Requete Rest pour supprimer une salle avec Id: {}", id);
		try {
			salleService.delete(id);
		} catch (EntityNotDeletedException e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		}
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

}
