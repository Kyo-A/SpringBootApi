package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Voiture;

public interface VoitureRepository extends JpaRepository<Voiture, Long>{
	
	List<Voiture> findByPersonneNum(Long personneNum);
	 
	Optional<Voiture> findByNumAndPersonneNum(Long num, Long personneNum);

}
