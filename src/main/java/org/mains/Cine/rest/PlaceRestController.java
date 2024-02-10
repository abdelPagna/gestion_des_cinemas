package org.mains.Cine.rest;

import java.util.HashMap;
import java.util.Map;

import org.mains.Cine.dto.PlaceDto;
import org.mains.Cine.exceptions.DuplicateNameException;
import org.mains.Cine.exceptions.EmptyNameException;
import org.mains.Cine.exceptions.EntityNotDeletedException;
import org.mains.Cine.exceptions.EntityNotFoundException;
import org.mains.Cine.services.PlaceService;
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
@RequestMapping("/api/v1/places")
@AllArgsConstructor
@Slf4j
public class PlaceRestController {
	private final PlaceService placeService;

	@GetMapping("/{id}")
	public ResponseEntity<?> getPlaceById(@PathVariable(name = "id") String id) {
		log.info("Requete Rest pour rétourner une place avec Id: {}", id);
		Map<String, Object> map=new HashMap<>();
		try {
			map.put("place", placeService.getOne(id));
		} catch (EntityNotFoundException e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		}
		return ResponseEntity.ok(map);
	}

	@GetMapping("")
	public ResponseEntity<?> places(){
		log.info("Requete Rest pour rétourner la liste des places");
		Map<String, Object> map=new HashMap<>();
		map.put("places", placeService.findAllPlaces());
		return ResponseEntity.ok(map);
	}

	@PostMapping("")
	public ResponseEntity<?> savePlace(@RequestBody PlaceDto placeDto) {
		log.info("Requete Rest pour enregister une place");
		Map<String, Object> map=new HashMap<>();
		try {
			map.put("place", placeService.save(placeDto));
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
	public ResponseEntity<?> updatePlace(@PathVariable(name = "id") String id, @RequestBody PlaceDto placeDto) throws Exception {
		log.info("Requete Rest pour mettre à jour une place");
		Map<String, Object> map=new HashMap<>();
		map.put("place", placeService.update(placeDto,id));
		return ResponseEntity.ok(map);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePlace(@PathVariable(name = "id") String id) {
		log.info("Requete Rest pour supprimer une place avec Id: {}", id);
		try {
			placeService.delete(id);
		} catch (EntityNotDeletedException e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		}
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

}
