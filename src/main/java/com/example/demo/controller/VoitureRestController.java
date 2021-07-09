package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Voiture;
import com.example.demo.repository.PersonneRepository;
import com.example.demo.repository.VoitureRepository;

@RestController
public class VoitureRestController {
	
	@Autowired
	private PersonneRepository personneRepository;

	@Autowired
	private VoitureRepository voitureRepository;
	
	@GetMapping("/personnes/{personneId}/voitures")
	public List<Voiture> getCarsByPerson(@PathVariable("personneId") Long personneId) {
		return voitureRepository.findByPersonneNum(personneId);
	}
	
	@PostMapping("/personnes/{personneId}/voitures")
	public Voiture save(@PathVariable(value = "personneId") Long personneId, @Valid @RequestBody Voiture voiture)
			throws ResourceNotFoundException {
		return personneRepository.findById(personneId).map(personne -> {
			voiture.setPersonne(personne);
			return voitureRepository.save(voiture);
		}).orElseThrow(() -> new ResourceNotFoundException("personne not found"));
	}
	
	@PutMapping("/personnes/{personneId}/voitures/{voitureId}")
	public Voiture updateCourse(@PathVariable(value = "personneId") Long personneId,
			@PathVariable(value = "voitureId") Long voitureId, @Valid @RequestBody Voiture voitureRequest)
			throws ResourceNotFoundException {
		if (!personneRepository.existsById(personneId)) {
			throw new ResourceNotFoundException("personneId not found");
		}
		return voitureRepository.findById(voitureId).map(voiture -> {
			voiture.setMarque(voitureRequest.getMarque());
			voiture.setModele(voitureRequest.getModele());
			return voitureRepository.save(voiture);
		}).orElseThrow(() -> new ResourceNotFoundException("car id not found"));
	}

	@DeleteMapping("/personnes/{personneId}/voitures/{voitureId}")
	public ResponseEntity<?> deleteCourse(@PathVariable(value = "personneId") Long personneId,
			@PathVariable(value = "voitureId") Long voitureId) throws ResourceNotFoundException {
		return voitureRepository.findByNumAndPersonneNum(voitureId, personneId).map(voiture -> {
			voitureRepository.delete(voiture);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException(
				"Car not found with id " + voitureId + " and personneId " + personneId));
	}


}
