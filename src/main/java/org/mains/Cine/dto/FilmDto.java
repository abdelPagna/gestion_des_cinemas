package org.mains.Cine.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class FilmDto {
	private String id;
	private String titre;
	private double duree;
	private String realisateur;
	private String description;
	private String photo;
	private Date dateSortie;
	private CategorieDto categorie;

}
