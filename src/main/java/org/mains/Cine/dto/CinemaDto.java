package org.mains.Cine.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class CinemaDto {
	private String id;
	private String name;
	private double longitude, latitude, altitude;
	private int nombreSalle;

}