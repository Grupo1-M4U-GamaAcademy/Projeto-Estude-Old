package com.m4ugrupo1.m4ugrupo1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.m4ugrupo1.m4ugrupo1.dto.UserDto;
import com.m4ugrupo1.m4ugrupo1.service.core.impl.UserServiceImpl;

@RestController
@RequestMapping("/user")
@ResponseBody
@Controller
public class UserController {
	
	
	private UserServiceImpl userService;
	
	@Autowired
	public UserController(UserServiceImpl userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	@ResponseBody
	public String newUser(Model model) {
		model.addAttribute("userdto", new UserDto());
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public String saveUser(UserDto userDto, @RequestParam("imageUrl") MultipartFile image, Model model) {
		try {
			userService.createUser(userDto, image);
			return "registered";
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e);
			return "error";
		}
	}

}
