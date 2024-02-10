package org.mains.Cine.mapper;

import org.mains.Cine.domain.Ticket;
import org.mains.Cine.dto.TicketDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface TicketMapper extends EntityMapper<TicketDto, Ticket>{

}
