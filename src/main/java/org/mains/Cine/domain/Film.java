package org.mains.Cine.domain;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Table(name = "films")
public class Film {
	@Id @GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String titre;
	private double duree;
	private String realisateur;
	private String description;
	private String photo;
	private Date dateSortie;
	@OneToMany(mappedBy = "film")
	private List<ProjectionFilm> projections;
	@ManyToOne
	private Categorie categorie;

}
