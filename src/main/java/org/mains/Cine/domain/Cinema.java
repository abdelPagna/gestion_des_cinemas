package org.mains.Cine.domain;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "cinemas")
public class Cinema {
	@Id @GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String name;
	private double longitude, latitude, altitude;
	private int nombreSalle;
	@ManyToOne
	private Ville ville;
	@OneToMany(mappedBy = "cinema")
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<Salle> salles;
}
