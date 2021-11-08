package com.m4ugrupo1.m4ugrupo1.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.m4ugrupo1.m4ugrupo1.authentication.UserRepository;
import com.m4ugrupo1.m4ugrupo1.dto.CourseDto;
import com.m4ugrupo1.m4ugrupo1.model.Course;
import com.m4ugrupo1.m4ugrupo1.model.Teachers;
import com.m4ugrupo1.m4ugrupo1.model.User;
import com.m4ugrupo1.m4ugrupo1.repository.CourseRepository;
import com.m4ugrupo1.m4ugrupo1.repository.RegistrationRepository;
import com.m4ugrupo1.m4ugrupo1.repository.TeachersRepository;
import com.m4ugrupo1.m4ugrupo1.service.core.impl.CourseServiceImpl;

@RestController
@RequestMapping("/course")
@ResponseBody
@Controller
public class CourseController {
	
	
	private CourseServiceImpl courseService;
	private CourseRepository courseRepository;
	private RegistrationRepository registrationRepository;
	private UserRepository userRepository;
	private TeachersRepository teachersRepository;
	
	@Autowired
	public CourseController(CourseServiceImpl courseService, CourseRepository courseRepository,
			RegistrationRepository registrationRepository, UserRepository userRepository,
			TeachersRepository teachersRepository) {
		
		this.courseService = courseService;
		this.courseRepository = courseRepository;
		this.registrationRepository = registrationRepository;
		this.userRepository = userRepository;
		this.teachersRepository = teachersRepository;
	}
	
	@GetMapping("/add/{teachers_id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseBody
	public String addCourse(@PathVariable Long teachers_id, Model model) {
		
		try {
			Teachers current = teachersRepository.findById(teachers_id).get();
			model.addAttribute("course", new CourseDto());
			model.addAttribute("teachers", current);
			
			return "courses/courses-add";
		
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e);
			
			return "error";
		}
	}
	
	@PostMapping("/add/{teachers_id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseBody
	public String saveCourse(@PathVariable Long teachers_id, CourseDto course, Model model) {
		
		try {
			Teachers current = teachersRepository.findById(teachers_id).get();
			course.setTeachers(current);
			courseService.create(course);
			
			return "redirect:/courses";
		
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e);
			
			return "error";
		}
	}
	
	@GetMapping("/edit/{course_id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseBody
	public String getCourseForUpdate(@PathVariable Long course_id, Model model) {
		
		try {
			Course currentCourse = courseRepository.findById(course_id).get();
			model.addAttribute("course", currentCourse);
			
			return "courses/course-edit";
		
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e);
			
			return "error";
		}
	}
	
	@PostMapping("/edit/{teachers_id}/{course_id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseBody
	public String updateCourse(@PathVariable Long teachers_id, @PathVariable Long course_id,
			Course course, Model model, RedirectAttributes attributes) {
		
		try { 
			Teachers currentTeachers = teachersRepository.findById(teachers_id).get();
			course.setTeachers(currentTeachers);
			
			courseService.update(course, course_id);
			attributes.addAttribute("course_id", course_id);
			
			return "redirect:/courses/{course_id}";
		
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e);
			
			return "error";
		}
	}
	
	@GetMapping("/courses")
	@ResponseBody
	public String getCoursesList(Model model) {
		
		List<Course> courses = courseService.getAll();
		model.addAttribute("courses", courses);
		
		return "courses/courses";
	}
	
	@GetMapping("/delete/{course_id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseBody
	public String deleteCourse(@PathVariable Long course_id, Model model) {
		
		try {
			Course currentCourse = courseRepository.findById(course_id).get();
			courseService.delete(currentCourse);
			
			return "redirect:/courses";
		
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e);
			
			return "error";
		}
	}
	
	@GetMapping("/{course_id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	@ResponseBody
	public String getCourseDetail(@PathVariable Long course_id, Authentication authentication, Model model) {
		
		String username = authentication.getName();
		boolean registered = false;
		
		try {
			Course course = courseRepository.findById(course_id).get();
			User user = userRepository.findByUsername(username);
			
			if (null != registrationRepository.findByCourseAndUser(course, user)) {
				registered = true;
			}
			model.addAttribute("course", course);
			model.addAttribute("registered", registered);
			return "courses/course-detail";
		
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e);
			return "error";
		}
	}

}
