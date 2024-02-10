package org.mains.Cine.services;

import java.util.List;

import org.mains.Cine.dto.TicketDto;
import org.mains.Cine.exceptions.EntityNotDeletedException;
import org.mains.Cine.exceptions.EntityNotFoundException;


public interface TicketService {
	 List<TicketDto> findAllTickets();
	 TicketDto getOne(String id) throws EntityNotFoundException;
	 TicketDto save(TicketDto ticketDto);
	 void delete(String id) throws EntityNotDeletedException;
	 TicketDto update(TicketDto ticketDto, String id) throws EntityNotFoundException;
}
