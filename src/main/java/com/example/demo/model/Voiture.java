package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Voiture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long num;

	private String modele;

	private String marque;

	public Voiture() {
		super();
	}
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	private Personne personne;

	public Voiture(Long num, String modele, String marque) {
		super();
		this.num = num;
		this.modele = modele;
		this.marque = marque;
	}

	public Voiture(Long num, String modele, String marque, Personne personne) {
		super();
		this.num = num;
		this.modele = modele;
		this.marque = marque;
		this.personne = personne;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
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

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	@Override
	public String toString() {
		return "Voiture [num=" + num + ", modele=" + modele + ", marque=" + marque + "]";
	}

}
