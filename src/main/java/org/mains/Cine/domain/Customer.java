package org.mains.Cine.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Table(name = "customers")
public class Customer {
	@Id @GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String name;
	private String email;
	private String numeroTelephone;

}
