package org.mains.Cine.rest;

import java.util.HashMap;
import java.util.Map;

import org.mains.Cine.dto.CategorieDto;
import org.mains.Cine.exceptions.DuplicateNameException;
import org.mains.Cine.exceptions.EmptyNameException;
import org.mains.Cine.exceptions.EntityNotDeletedException;
import org.mains.Cine.exceptions.EntityNotFoundException;
import org.mains.Cine.services.CategorieService;
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
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
@Slf4j
public class CategorieRestController {

	//private final CategorieRepository categorieRepository;
	private final CategorieService categorieService;

	@GetMapping("")
	public ResponseEntity<?> categories(){
		log.info("Requete Rest pour rétourner la liste des catégories");
		Map<String, Object> map=new HashMap<>();
		map.put("categories", categorieService.findAllCategories());
		return ResponseEntity.ok(map);
	}


	@GetMapping("/{id}")
	public ResponseEntity<?> getCategorieById(@PathVariable(name = "id") String id) {
		log.info("Requete Rest pour rétourner une catégorie avec Id: {}", id);
		Map<String, Object> map=new HashMap<>();
		try {
			map.put("categorie", categorieService.getOne(id));
		} catch (EntityNotFoundException e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		}
		return ResponseEntity.ok(map);
	}

	@PostMapping("")
	public ResponseEntity<?> saveCategorie(@RequestBody CategorieDto categorieDto) {
		log.info("Requete Rest pour enregister une categorie");
		Map<String, Object> map=new HashMap<>();
		try {
			map.put("categorie", categorieService.save(categorieDto));
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
	public ResponseEntity<?> updateCategorie(@RequestBody CategorieDto categorieDto, @PathVariable(name = "id") String id) {
		log.info("Requete Rest pour mettre à jour une catégorie");
		Map<String, Object> map=new HashMap<>();
		try {
			map.put("categorie", categorieService.update(categorieDto,id));
		} catch (EntityNotFoundException | EmptyNameException | DuplicateNameException e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		}
		return ResponseEntity.ok(map);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategorie(@PathVariable(name = "id") String id) {
		log.info("Requete Rest pour supprimer une catégorie avec Id: {}", id);
		try {
			categorieService.delete(id);
		} catch (EntityNotDeletedException e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		}
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}
