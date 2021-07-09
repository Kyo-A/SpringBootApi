package com.example.demo.dto;

import com.example.demo.model.Personne;

public class VoitureDto {

	private String modele;

	private String marque;
	
	private Personne personne;

	public VoitureDto() {
		super();
	}

	public VoitureDto(String modele, String marque) {
		super();
		this.modele = modele;
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}
	
	

}
