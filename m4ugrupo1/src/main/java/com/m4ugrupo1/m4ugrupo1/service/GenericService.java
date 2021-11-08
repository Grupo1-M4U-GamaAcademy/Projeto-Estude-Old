package com.m4ugrupo1.m4ugrupo1.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface GenericService<T, K> {
	
	void create(T t);
	
	void update(T t);
	
	void delete(T t);
	
	List<T> getAll();
	
	T findById(K k);

}
