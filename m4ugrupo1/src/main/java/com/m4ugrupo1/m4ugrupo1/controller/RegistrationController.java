package com.m4ugrupo1.m4ugrupo1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.m4ugrupo1.m4ugrupo1.authentication.UserRepository;
import com.m4ugrupo1.m4ugrupo1.model.Course;
import com.m4ugrupo1.m4ugrupo1.model.User;
import com.m4ugrupo1.m4ugrupo1.repository.CourseRepository;
import com.m4ugrupo1.m4ugrupo1.service.core.impl.RegistrationServiceImpl;

@RestController
@RequestMapping("/registration")
@PreAuthorize("hasRole('ROLE_USER')")
@ResponseBody
@Controller
public class RegistrationController {
	
	
	
	private UserRepository userRepository;
	private CourseRepository courseRepository;
	private RegistrationServiceImpl registrationService;
	
	@Autowired
	public RegistrationController(RegistrationServiceImpl registrationService, UserRepository userRepository, CourseRepository courseRepository) {
        
		this.registrationService = registrationService;
		this.userRepository = userRepository;
		this.courseRepository = courseRepository;
	}
	
	@GetMapping("/save/{course_id}")
	@ResponseBody
	public String saveRegistration(@PathVariable Long course_id, Authentication authentication, Model model) {

		try {
			
			String username = authentication.getName();
			registrationService.createRegistration(course_id, username);
			User user = userRepository.findByUsername(username);
			Course course = courseRepository.findById(course_id).get();
			model.addAttribute("course", course);
			model.addAttribute("user", user);
			
			return "registration-success";
		
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e);
			
			return "error";
		}
	
	
	}
}

