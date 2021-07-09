package com.example.demo.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.PersonneDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Personne;
import com.example.demo.repository.PersonneRepository;
import com.example.demo.service.PersonneService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
public class PersonneRestController {

	@Autowired
	private PersonneService personneService;

	@ApiOperation(value = "Récupère toutes les personnes !")
	@RequestMapping(value = "/personnes", method = RequestMethod.GET)
	public List<Personne> getPersonnes() {
		List<Personne> personnes = personneService.getAll();
		return personnes;
	}
	
	@ApiOperation(value = "Récupère une personne grâce à son ID !")
	@RequestMapping(value = "/personnes/{id}", method = RequestMethod.GET)
	public ResponseEntity<Personne> getPersonneById(@PathVariable Long id) {
		Personne personne = personneService.getById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Personne not found :" + id));
		return ResponseEntity.ok().body(personne);

	}
	
	@ApiOperation(value = "Enregistre un personne !")
	@RequestMapping(value = "/personnes", method = RequestMethod.POST)
	@Transactional
	public ResponseEntity<Personne> save(@Valid @RequestBody PersonneDto personneDto) {
		Personne personne = new Personne();
		personne.setNom(personneDto.getNom());
		personne.setPrenom(personneDto.getPrenom());
		Personne personneAjoute = personneService.saveOrUpdate(personne);
		return new ResponseEntity<Personne>(personneAjoute, HttpStatus.OK);	
	}
	
	@ApiOperation(value = "Met à jour une personne grâce à son ID !")
	@PutMapping("/personnes/{id}")
	@Transactional
	public ResponseEntity<Personne> updatePersonne(@Valid @RequestBody PersonneDto personneDto,
			@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		Personne personneToUpdate = (personneService.getById(id))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personne not found :: " + id));
		personneToUpdate.setNom(personneDto.getNom());
		personneToUpdate.setPrenom(personneDto.getPrenom());
		Personne personneUpdated = personneService.saveOrUpdate(personneToUpdate);
		return new ResponseEntity<Personne>(personneUpdated, HttpStatus.OK);

	}

	@ApiOperation(value = "Supprime une personne grâce à son ID !")
	@DeleteMapping("/personnes/{id}")
	@Transactional
	public Map<String, Boolean> deletePersonne(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		Personne personneToUpdate = (personneService.getById(id))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personne not found :: " + id));
		personneService.delete(personneToUpdate.getNum());
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
}
