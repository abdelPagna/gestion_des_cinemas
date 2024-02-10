package org.mains.Cine.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@Table(name = "tickets")
public class Ticket {
	@Id @GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@Column(name = "name_client")
	private String nameClient;
	private double prix;
	@Column(name = "code_payement", unique = false, nullable = true)
	private String codePayement;
	private boolean reservee;
	@ManyToOne
	@JoinColumn(name = "projection_film_id")
	private ProjectionFilm projectionFilm;
	@ManyToOne
	@JoinColumn(name = "place_id")
	private Place place;
}