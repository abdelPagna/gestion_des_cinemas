package org.mains.Cine.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
@Table(name = "categories")
public class Categorie {
	@Id @GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@Column(length = 100)
	private String name;
	@OneToMany(mappedBy = "categorie")
	private List<Film> films;
}
