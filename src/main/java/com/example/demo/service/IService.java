package com.example.demo.service;

import java.util.Collection;
import java.util.Optional;

public interface IService<T> {
	
	Collection<T> getAll();
	
	Optional<T> getById(Long id);
	
	T saveOrUpdate(T o);
	
	void delete(Long id);

}
