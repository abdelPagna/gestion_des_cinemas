package org.mains.Cine.rest;

import java.util.HashMap;
import java.util.Map;

import org.mains.Cine.dto.CustomerDto;
import org.mains.Cine.exceptions.DuplicateNameException;
import org.mains.Cine.exceptions.EmptyNameException;
import org.mains.Cine.exceptions.EntityNotDeletedException;
import org.mains.Cine.exceptions.EntityNotFoundException;
import org.mains.Cine.services.CustomerService;
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
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
@Slf4j
public class CustomerClientRestController {

	//private final ClientRepository ClientRepository;
	private final CustomerService customerService;

	@GetMapping("")
	public ResponseEntity<?> Clients(){
		log.info("Requete Rest pour rétourner la liste des clients");
		Map<String, Object> map=new HashMap<>();
		map.put("clients", customerService.findAllCustomers());
		return ResponseEntity.ok(map);
	}


	@GetMapping("/{id}")
	public ResponseEntity<?> getClientById(@PathVariable(name = "id") String id) {
		log.info("Requete Rest pour rétourner un client avec Id: {}", id);
		Map<String, Object> map=new HashMap<>();
		try {
			map.put("client", customerService.getOne(id));
		} catch (EntityNotFoundException e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		}
		return ResponseEntity.ok(map);
	}

	@PostMapping("")
	public ResponseEntity<?> saveClient(@RequestBody CustomerDto customerDto) {
		log.info("Requete Rest pour enregister un client");
		Map<String, Object> map=new HashMap<>();
		try {
			map.put("client", customerService.save(customerDto));
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
	public ResponseEntity<?> updateClient(@RequestBody CustomerDto customerDto, @PathVariable(name = "id") String id) {
		log.info("Requete Rest pour mettre à jour un client");
		Map<String, Object> map=new HashMap<>();
		try {
			map.put("client", customerService.update(customerDto,id));
		} catch (EntityNotFoundException | EmptyNameException | DuplicateNameException e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		}
		return ResponseEntity.ok(map);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteClient(@PathVariable(name = "id") String id) {
		log.info("Requete Rest pour supprimer un client avec Id: {}", id);
		try {
			customerService.delete(id);
		} catch (EntityNotDeletedException e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		}
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}
