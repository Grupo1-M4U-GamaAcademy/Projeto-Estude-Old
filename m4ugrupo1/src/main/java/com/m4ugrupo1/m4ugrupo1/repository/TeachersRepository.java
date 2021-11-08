package com.m4ugrupo1.m4ugrupo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.m4ugrupo1.m4ugrupo1.model.Teachers;

@Repository
public interface TeachersRepository extends JpaRepository<Teachers, Long>{
	
	

}
