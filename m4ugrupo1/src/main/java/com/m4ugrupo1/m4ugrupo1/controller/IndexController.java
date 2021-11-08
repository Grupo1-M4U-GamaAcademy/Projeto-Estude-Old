package com.m4ugrupo1.m4ugrupo1.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/")
@Slf4j
@ResponseBody
@Controller
public class IndexController {
	
	@GetMapping({"/", "/index"})
	@ResponseBody
	public String index() {
		log.info("Home Page");
		
		return "index";
	}
	
	@GetMapping("/discover")
	@ResponseBody
	public String discover() {
		
		return "discover";
	}
	
	@GetMapping("/login")
	@ResponseBody
	public String loginPage(Model model) {
		return "login";
	}
	
	@GetMapping("/logout-success")
	@ResponseBody
	public String logoutPage(Model model) {
		return "logout";
	}

}
