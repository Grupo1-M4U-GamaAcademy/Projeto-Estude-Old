package com.m4ugrupo1.m4ugrupo1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.m4ugrupo1.m4ugrupo1.authentication.UserRepository;
import com.m4ugrupo1.m4ugrupo1.model.Registration;
import com.m4ugrupo1.m4ugrupo1.model.User;
import com.m4ugrupo1.m4ugrupo1.repository.RegistrationRepository;
import com.m4ugrupo1.m4ugrupo1.service.core.impl.UserServiceImpl;

@RestController
@Controller
public class SecurityController {
	
	
	private UserRepository userRepository;
	private RegistrationRepository registrationRepository;
	private UserServiceImpl userService;
	
	@Autowired
	public SecurityController(UserRepository userRepository, RegistrationRepository registrationRepository,
			UserServiceImpl userService) {
		
		this.userRepository = userRepository;
		this.registrationRepository = registrationRepository;
		this.userService = userService;
	}
	
	@GetMapping("/profile")
	@ResponseBody
	public String getUserProfile(Authentication authentication, Model model) {
		
		try {
			String currentUsername = authentication.getName();
			User user = userRepository.findByUsername(currentUsername);
			List<Registration> registrations = registrationRepository.findAllByUsername(user);
			int numCourses = registrations.size();
			model.addAttribute("user", user);
			model.addAttribute("registrations", registrations);
			model.addAttribute("numCourses", numCourses);
			return "user/profile";
		
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e);
			return "error";
		}
		
	}
	
	@GetMapping("/user/edit/{user_id}")
	@ResponseBody
	public String getForEdit(@PathVariable Long user_id, Authentication authentication, Model model) {
		
		try {
			String currentUsername = authentication.getName();
			User current = userRepository.findById(user_id).get();
			
			if (currentUsername.equals(current.getUserName())) {
				model.addAttribute(current);
				return "user/user-edit";
			
			}else { 
				throw new Exception("Authentication error");
			}
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e);
			return "error";
		}
	}
	
	@PostMapping("/user/edit/{user_id}")
	@ResponseBody
	public String updateUser(@PathVariable Long user_id, Authentication authentication, User user, Model model) {
		
		try {
			User current = userRepository.findById(user_id).get();
			String currentUsername = authentication.getName();
			
			if (currentUsername.equals(current.getUserName())) {
				
				current.setName(user.getName());
				current.setLastName(user.getLastName());
				current.setEmail(user.getEmail());
				current.setImageUrl(user.getImageUrl());
				userService.update(current);
				
				return "redirect:/profile";
			}else {
				throw new Exception("Authentication error");
			}
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e);
			return "error";
			
		}
	}

	@PostMapping("/user/patch/{user_id}")
	@ResponseBody
	public String patchUser(@PathVariable Long user_id, Authentication authentication, User user, Model model) {
		
		try {
			User current = userRepository.findById(user_id).get();
			String currentUsername = authentication.getName();
			
			if (currentUsername.equals(current.getUserName())) {
				current.setDetails(user.getDetails());
				userService.patch(current);
				
				return "redirect:/profile";
			
			}else {
				throw new Exception("Authentication error");
			}
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e);
			return "error";
		}
	}
}
