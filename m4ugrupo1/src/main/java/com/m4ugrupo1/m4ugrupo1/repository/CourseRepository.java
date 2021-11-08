package com.m4ugrupo1.m4ugrupo1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.m4ugrupo1.m4ugrupo1.model.Course;
import com.m4ugrupo1.m4ugrupo1.model.Teachers;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
	
	
	public List<Course> findByNameCourse(String name);
	public List<Course> findAllByTeachers(Teachers teachers);

}
