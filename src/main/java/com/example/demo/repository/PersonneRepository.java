package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Personne;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long>{

	Optional<Personne> findByNum(Long num);
}
