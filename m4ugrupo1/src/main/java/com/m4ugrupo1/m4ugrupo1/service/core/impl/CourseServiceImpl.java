package com.m4ugrupo1.m4ugrupo1.service.core.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m4ugrupo1.m4ugrupo1.dto.CourseDto;
import com.m4ugrupo1.m4ugrupo1.model.Course;
import com.m4ugrupo1.m4ugrupo1.model.Teachers;
import com.m4ugrupo1.m4ugrupo1.repository.CourseRepository;

@Service
public class CourseServiceImpl {
	
	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	public void create(CourseDto courseDto) throws Exception{
	if (null != courseRepository.findByNameCourse(courseDto.getNameCourse())) {
		throw new Exception("There is already a course with that name." + courseDto.getNameCourse());
	}
	
	String nameCourse = courseDto.getNameCourse();
	String descriptionCourse = courseDto.getDescriptionCourse();
	String detailsCourse = courseDto.getDetailsCourse();
	String difficultyCourse = courseDto.getDifficultyCourse();
	String urlCourse = courseDto.getUrl();
	String imageUrl = courseDto.getImageUrl();
	Teachers teachers = courseDto.getTeachers();
	Course course = new Course(nameCourse, descriptionCourse, detailsCourse, difficultyCourse, urlCourse, imageUrl, teachers);
	
	courseRepository.save(course);
}
	
	public void update(Course course, Long id_course) {
		Course currentCourse = courseRepository.findById(id_course).get();
		
		currentCourse.setNameCourse(course.getNameCourse());
		currentCourse.setDescriptionCourse(course.getDescriptionCourse());
		currentCourse.setDetailsCourse(course.getDetailsCourse());
		currentCourse.setDifficultyCourse(course.getDifficultyCourse());
		currentCourse.setUrlCourse(course.getUrlCourse());
		currentCourse.setImageCourse(course.getImageCourse());
		currentCourse.setTeachers(course.getTeachers());
		
		courseRepository.save(currentCourse);
	}
	
	public void delete(Course course) { courseRepository.delete(course);}
	
	public List<Course> getAll(){
		return courseRepository.findAll();
	}
}