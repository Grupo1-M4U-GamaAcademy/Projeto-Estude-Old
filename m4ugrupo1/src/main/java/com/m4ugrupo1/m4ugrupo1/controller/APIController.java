package com.m4ugrupo1.m4ugrupo1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.m4ugrupo1.m4ugrupo1.model.Course;
import com.m4ugrupo1.m4ugrupo1.model.Teachers;
import com.m4ugrupo1.m4ugrupo1.service.core.impl.CourseServiceImpl;
import com.m4ugrupo1.m4ugrupo1.service.core.impl.TeachersServiceImpl;

@RestController
@RequestMapping("/api")
@Controller
public class APIController {
	
	@Autowired
	private TeachersServiceImpl teachersService;
	private CourseServiceImpl courseService;
	
	@Autowired
	public APIController(TeachersServiceImpl teachersService, CourseServiceImpl courseService) {
		super();
		this.teachersService = teachersService;
		this.courseService = courseService;
	}
	
	@GetMapping("/teachers")
	@ResponseBody
	public List<Teachers> getAllTeachers(){
		return this.teachersService.getAll();
	}
	
	@GetMapping("/courses")
	@ResponseBody
	public List<Course> getAllCourse(){
		return this.courseService.getAll();
	}
	

}
