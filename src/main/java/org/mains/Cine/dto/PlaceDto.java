package org.mains.Cine.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class PlaceDto {
	private String id;
	private int numero;
	private double longitude, latitude, altitude;

}
