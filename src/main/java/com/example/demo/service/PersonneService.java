package com.example.demo.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Personne;
import com.example.demo.repository.PersonneRepository;

@Service(value="personneService")
public class PersonneService implements IService<Personne> {
	
	@Autowired
	private PersonneRepository personneRepository;

	@Override
	public List<Personne> getAll() {
		
		return personneRepository.findAll();
	}

	@Override
	public Optional<Personne> getById(Long id) {

		return personneRepository.findByNum(id);
	}

	@Override
	@Transactional(readOnly= false)
	public Personne saveOrUpdate(Personne personne) {
		return personneRepository.save(personne);
	}

	@Override
	@Transactional(readOnly=false)
	public void delete(Long id) {
		personneRepository.deleteById(id);

	}

}
