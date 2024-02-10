package org.mains.Cine.rest;

import java.util.HashMap;
import java.util.Map;

import org.mains.Cine.dto.VilleDto;
import org.mains.Cine.exceptions.DuplicateNameException;
import org.mains.Cine.exceptions.EmptyNameException;
import org.mains.Cine.exceptions.EntityNotDeletedException;
import org.mains.Cine.exceptions.EntityNotFoundException;
import org.mains.Cine.services.VilleService;
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
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/villes")
public class VilleRestController {
	//private VilleRepository villeRepository;
	private final VilleService villeService;

	@GetMapping("/{id}")
	public ResponseEntity<?> getVilleById(@PathVariable(name = "id") String id) {
		log.info("Requete Rest pour rétourner une ville avec Id: {}", id);
		Map<String, Object> map=new HashMap<>();
		try {
			map.put("ville", villeService.getOne(id));
		} catch (EntityNotFoundException e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		}
		return ResponseEntity.ok(map);
	}

	@GetMapping("")
	public ResponseEntity<?> villes(){
		log.info("Requete Rest pour rétourner la liste des villes");
		Map<String, Object> map=new HashMap<>();
		map.put("villes", villeService.findAllVilles());
		return ResponseEntity.ok(map);
	}

	@PostMapping("/villes")
	public ResponseEntity<?> saveVille(@RequestBody VilleDto villeDto) {
		log.info("Requete Rest pour enregister une ville");
		Map<String, Object> map=new HashMap<>();
		try {
			map.put("ville", villeService.save(villeDto));
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
	public ResponseEntity<?> updateVille(@PathVariable(name = "id") String id, @RequestBody VilleDto villeDto) {
		log.info("Requete Rest pour mettre à jour une ville");
		Map<String, Object> map=new HashMap<>();
		try {
			map.put("ville", villeService.update(villeDto,id));
		} catch (EntityNotFoundException | EmptyNameException | DuplicateNameException e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		}
		return ResponseEntity.ok(map);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteVille(@PathVariable(name = "id") String id) {
		log.info("Requete Rest pour supprimer une ville avec Id: {}", id);
		try {
			villeService.delete(id);
		} catch (EntityNotDeletedException e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		}
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

}
