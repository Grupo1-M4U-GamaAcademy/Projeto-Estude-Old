package com.m4ugrupo1.m4ugrupo1.service.core.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m4ugrupo1.m4ugrupo1.dto.TeachersDto;
import com.m4ugrupo1.m4ugrupo1.model.Teachers;
import com.m4ugrupo1.m4ugrupo1.repository.TeachersRepository;

@Service
public class TeachersServiceImpl {
	
	@Autowired
	private TeachersRepository teachersRepository;
	
	@Autowired
	public TeachersServiceImpl(TeachersRepository teachersRepository) {
		this.teachersRepository = teachersRepository;
	}
	
	public void create(TeachersDto teachersDto) {
		String name = teachersDto.getName();
		String lastName = teachersDto.getLastName();
		String email = teachersDto.getEmail();
		String descriptionTeachers = teachersDto.getDescriptionTeachers();
		String detailsTeachers = teachersDto.getDetailsTeachers();
		String imageUrl = teachersDto.getImageUrl();
		Teachers teachers = new Teachers(name, lastName, email, descriptionTeachers, imageUrl);
		
		teachersRepository.save(teachers);
	}
	
	public List<Teachers> getAll(){
		return teachersRepository.findAll();
	}
	
	public void update(Teachers teachers) {
		Teachers currentTeachers = teachersRepository.findById(teachers.getTeachers_id()).get();
		
		currentTeachers.setName(teachers.getName());
		currentTeachers.setLastName(teachers.getLastName());
		currentTeachers.setEmail(teachers.getEmail());
		currentTeachers.setDescriptionTeachers(teachers.getDescriptionTeachers());
		currentTeachers.setImageUrl(teachers.getImageUrl());
		
		teachersRepository.save(currentTeachers);
	}
	
	public void patch(Teachers teachers) {
		Teachers current = teachersRepository.findById(teachers.getTeachers_id()).get();
		
		current.setDetailsTeachers(teachers.getDetailsTeachers());
		
		teachersRepository.save(current);
	}
	
	public void delete(Teachers teachers) {
		teachersRepository.delete(teachers);
	}

}
