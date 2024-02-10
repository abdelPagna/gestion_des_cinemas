package org.mains.Cine.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class TicketDto {
	private String id;
	private String nameClient;
	private double prix;
	private String codePayement;
	private boolean reservee;

}
