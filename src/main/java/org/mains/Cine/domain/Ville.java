package org.mains.Cine.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@Table(name = "villes")
public class Ville {
	@Id @GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String name;
	private double longitude, latitude, altitude;
	@OneToMany(mappedBy = "ville")
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<Cinema> cinemas;
}