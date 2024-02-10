package org.mains.Cine.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@Table(name = "projection_films")
public class ProjectionFilm {
	@Id @GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private Date dateProjection;
	private double prix;
	@ManyToOne
	@JsonProperty(access = Access.WRITE_ONLY)
	private Salle salle;
	@ManyToOne
	private Film film;
	@OneToMany(mappedBy = "projectionFilm")
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<Ticket> tickets;

}
