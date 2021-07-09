package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Personne {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long num;
	
	private String nom;
	
	private String prenom;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=true)
	private List<Voiture> voitures = new ArrayList<>();
	
	public Personne() {
		super();
	}

	public Personne(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}

	public Personne(String nom, String prenom, List<Voiture> voitures) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.voitures = voitures;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
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

	public List<Voiture> getVoitures() {
		return voitures;
	}

	public void setVoitures(List<Voiture> voitures) {
		this.voitures = voitures;
	}

	@Override
	public String toString() {
		return "Personne [num=" + num + ", nom=" + nom + ", prenom=" + prenom + "]";
	}
		
	
}
