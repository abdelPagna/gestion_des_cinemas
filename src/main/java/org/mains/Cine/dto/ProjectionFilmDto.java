package org.mains.Cine.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class ProjectionFilmDto {
	private String id;
	private Date dateProjection;
	private double prix;

}
