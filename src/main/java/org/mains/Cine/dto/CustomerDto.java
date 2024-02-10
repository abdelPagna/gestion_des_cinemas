package org.mains.Cine.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class CustomerDto {
	private String id;
	private String name;
	private String email;
	private String numeroTelephone;
}
