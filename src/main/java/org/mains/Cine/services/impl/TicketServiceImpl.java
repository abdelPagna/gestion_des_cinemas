package org.mains.Cine.services.impl;

import java.util.List;
import java.util.Optional;

import org.mains.Cine.domain.Ticket;
import org.mains.Cine.dto.TicketDto;
import org.mains.Cine.exceptions.EntityNotDeletedException;
import org.mains.Cine.exceptions.EntityNotFoundException;
import org.mains.Cine.mapper.TicketMapper;
import org.mains.Cine.repositories.TicketRepository;
import org.mains.Cine.services.TicketService;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class TicketServiceImpl implements TicketService{
	private final TicketRepository ticketRepository;
	private final TicketMapper ticketMapper;

	@Override
	public List<TicketDto> findAllTickets() {
		List<Ticket> listTickets= ticketRepository.findAll();
		return ticketMapper.toDto(listTickets);
	}

	@Override
	public TicketDto getOne(String id) throws EntityNotFoundException {
		Optional<Ticket> ticket=ticketRepository.findById(id);
		if(ticket.isPresent()) {
			return ticketMapper.toDto(ticket.get());
		} else {
			throw new EntityNotFoundException("Ticket", id);
		}
	}

	@Override
	public TicketDto save(TicketDto ticketDto) {
		Ticket ticket = ticketMapper.toEntity(ticketDto);

	    // Logique métier
	    validateTicketName(ticket.getNameClient());
	   // checkDoublonTicket(ticket);

	    Ticket savedTicket = ticketRepository.save(ticket);

	    return ticketMapper.toDto(savedTicket);
	}

	private void validateTicketName(String ticketName) {
	    if (ticketName == null || ticketName.isEmpty()) {
	        throw new IllegalArgumentException("Le nom du client ne peut pas être vide");
	    }
	}

	/*
	 * private void checkDoublonTicket(Ticket ticket) { // Vérifier si un client
	 * avec le même nom existe déjà //Optional<Ticket> existingTicket =
	 * ticketRepository.findByNameIgnoreCase(ticket.getNameClient());
	 *
	 * if (existingTicket.isPresent()) { throw new
	 * IllegalStateException("Un client avec le même nom existe déjà");
	 *
	 * }
	 *
	 * }
	 */

	@Override
	public void delete(String id) throws EntityNotDeletedException {
		Optional<Ticket> ticket=ticketRepository.findById(id);
		if(ticket.isPresent()) {
			ticketRepository.delete(ticket.get());
		} else {
			throw new EntityNotDeletedException("Ticket", id);
		}
	}

	@Override
	public TicketDto update(TicketDto ticketDto, String id) throws EntityNotFoundException  {

		Ticket ticket = ticketRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Ticket", id));

	    // Logique métier
	    validateTicketName(ticketDto.getNameClient());
	    //checkDoublonTicket(ticketMapper.toEntity(ticketDto));

	    Ticket updatedTicket = ticketMapper.toEntity(ticketDto);
	    updatedTicket.setId(ticket.getId());
	    updatedTicket.setNameClient(ticket.getNameClient());
	    updatedTicket.setPrix(ticket.getPrix());
	    updatedTicket.setCodePayement(ticket.getCodePayement());
	    //updatedTicket.setReservee(ticket.isReservee());

	    updatedTicket = ticketRepository.save(updatedTicket);
	    return ticketMapper.toDto(updatedTicket);
	}



}
