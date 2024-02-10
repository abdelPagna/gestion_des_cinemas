package org.mains.Cine.rest;

import java.util.HashMap;
import java.util.Map;

import org.mains.Cine.dto.TicketDto;
import org.mains.Cine.exceptions.EntityNotDeletedException;
import org.mains.Cine.exceptions.EntityNotFoundException;
import org.mains.Cine.services.TicketService;
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
@RequestMapping("/api/v1/tickets")
@AllArgsConstructor
@Slf4j
public class TicketRestController {

	private final TicketService ticketService;

	@GetMapping("/{id}")
	public ResponseEntity<?> getTicketById(@PathVariable(name = "id") String id) {
		log.info("Requete Rest pour rétourner un ticket avec Id: {}", id);
		Map<String, Object> map=new HashMap<>();
		try {
			map.put("ticket", ticketService.getOne(id));
		} catch (EntityNotFoundException e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		}
		return ResponseEntity.ok(map);
	}

	@GetMapping("")
	public ResponseEntity<?> tickets(){
		log.info("Requete Rest pour rétourner la liste des tickets");
		Map<String, Object> map=new HashMap<>();
		map.put("tickets", ticketService.findAllTickets());
		return ResponseEntity.ok(map);
	}

	@PostMapping("/tickets")
	public ResponseEntity<?> saveTicket(@RequestBody TicketDto ticketDto) {
		log.info("Requete Rest pour enregister un ticket");
		Map<String, Object> map=new HashMap<>();
		map.put("ticket", ticketService.save(ticketDto));
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateTicket(@PathVariable(name = "id") String id, @RequestBody TicketDto ticketDto) {
		log.info("Requete Rest pour mettre à jour un ticket");
		Map<String, Object> map=new HashMap<>();
		try {
			map.put("ticket", ticketService.update(ticketDto,id));
		} catch (EntityNotFoundException e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		}
		return ResponseEntity.ok(map);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTicket(@PathVariable(name = "id") String id) {
		log.info("Requete Rest pour supprimer un ticket avec Id: {}", id);
		try {
			ticketService.delete(id);
		} catch (EntityNotDeletedException e) {
			log.info(e.getMessage());
			return ResponseEntity.notFound().header("message", e.getMessage()).build();
		}
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

}
