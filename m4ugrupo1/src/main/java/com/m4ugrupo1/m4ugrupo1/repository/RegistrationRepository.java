package com.m4ugrupo1.m4ugrupo1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.m4ugrupo1.m4ugrupo1.model.Course;
import com.m4ugrupo1.m4ugrupo1.model.Registration;
import com.m4ugrupo1.m4ugrupo1.model.User;


@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long>{
	
	
	public List<Registration> findByCourse(Course course);
	public List<Registration> findByCourseAndUser(Course course, User user);
	public List<Registration> findAllByUsername(User user);
	
	

}
