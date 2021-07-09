package com.example.demo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PersonneDto {
	
	@NotEmpty
	@Size(min = 2, message = "first name should have at least 2 characters")
	private String nom;
	@NotEmpty
	@Size(min = 2, message = "last name should have at least 2 characters")
	private String prenom;

	public PersonneDto(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

}
