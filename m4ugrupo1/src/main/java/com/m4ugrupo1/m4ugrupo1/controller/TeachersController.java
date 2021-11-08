package com.m4ugrupo1.m4ugrupo1.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.m4ugrupo1.m4ugrupo1.dto.TeachersDto;
import com.m4ugrupo1.m4ugrupo1.model.Course;
import com.m4ugrupo1.m4ugrupo1.model.Teachers;
import com.m4ugrupo1.m4ugrupo1.repository.CourseRepository;
import com.m4ugrupo1.m4ugrupo1.repository.TeachersRepository;
import com.m4ugrupo1.m4ugrupo1.service.core.impl.TeachersServiceImpl;

@RestController
@RequestMapping("/teachers")
@ResponseBody
@Controller
	public class TeachersController {

	  
		private TeachersServiceImpl teachersService;
		private TeachersRepository teachersRepository;
		private CourseRepository courseRepository;

		@Autowired
		public TeachersController(TeachersServiceImpl teachersService, TeachersRepository teachersRepository,
				CourseRepository courseRepository) {
			this.teachersService = teachersService;
			this.teachersRepository = teachersRepository;
			this.courseRepository = courseRepository;
		}

		@GetMapping("/add")
		@PreAuthorize("hasRole('ROLE_USER')")
		@ResponseBody
		public String addTeachers(Model model) {
			
			model.addAttribute("teachers", new TeachersDto());
			return "teachers/teachers-add";
		}

		@PostMapping("/save")
		@PreAuthorize("hasRole('ROLE_USER')")
		@ResponseBody
		public String saveTeachers(TeachersDto teachers) {
			teachersService.create(teachers);

			return "redirect:/teachers";
		}

		@GetMapping("/edit/{teachers_id}")
		@PreAuthorize("hasRole('ROLE_ADMIN')")
		@ResponseBody
		public String getTeachersForUpdate(@PathVariable Long teachers_id, Model model) {
			try {
				Teachers currentTeachers = teachersRepository.findById(teachers_id).get();
				model.addAttribute("teachers", currentTeachers);
				
				return "teachers/teachers-edit";
			
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("error", e);
				return "error";
			}
		}

		@PostMapping("/update/{teachers_id}")
		@PreAuthorize("hasRole('ROLE_ADMIN')")
		@ResponseBody
		public String updateTeachers(@PathVariable Long teachers_id, Teachers teachers, RedirectAttributes attributes,
				Model model) {

			try {
				Teachers currentTeachers = teachersRepository.findById(teachers_id).get();
				currentTeachers.setName(teachers.getName());
				currentTeachers.setLastName(teachers.getLastName());
				currentTeachers.setEmail(teachers.getEmail());
				currentTeachers.setDescriptionTeachers(teachers.getDescriptionTeachers());
				currentTeachers.setImageUrl(teachers.getImageUrl());

				teachersService.update(teachers);
				attributes.addAttribute("teachers_id", teachers_id);

				return "redirect:/teachers/{teachers_id}";
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("error", e);
				return "error";
			}
		}

		@PostMapping("/patch/{teachers_id}")
		@ResponseBody
		public String patchTeachers(@PathVariable Long teachers_id, Teachers teachers, RedirectAttributes attributes,
				Model model) {

			try {
				Teachers current = teachersRepository.findById(teachers_id).get();
				current.setDetailsTeachers(teachers.getDetailsTeachers());
				teachersService.patch(current);

				attributes.addAttribute("teachers_id", teachers_id);
				
				return "redirect:/teachers/{teachers_id}";
			
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("error", e);
				
				return "error";
			}
		}

		@GetMapping
		@PreAuthorize("hasRole('ROLE_USER')")
		@ResponseBody
		public String getTeachersList(Model model) {
			List<Teachers> teachers = teachersService.getAll();
			model.addAttribute("teachers", teachers);
			
			return "teachers/teachers";
		}

		@GetMapping("/delete/{teachers_id}")
		@PreAuthorize("hasRole('ROLE_ADMIN')")
		@ResponseBody
		public String deleteTeachers(@PathVariable Long teachers_id, Model model) {
			try {
				Teachers currentTeachers = teachersRepository.findById(teachers_id).get();
				teachersService.delete(currentTeachers);

				return "redirect:/teachers";
			
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("error", e);
				
				return "error";
			}
		}

		@GetMapping("/{teachers_id}")
		@PreAuthorize("hasRole('ROLE_USER')")
		@ResponseBody
		public String getTeachersDetails(@PathVariable Long teachers_id, Model model) {
			
			try {
				Teachers teachers = teachersRepository.findById(teachers_id).get();
				model.addAttribute("teachers", teachers);
				List<Course> courses = courseRepository.findAllByTeachers(teachers);
				model.addAttribute("courses", courses);
				
				return "teachers/teachers-detail";
			
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("error", e);
				return "error";
			}
		}
	}


