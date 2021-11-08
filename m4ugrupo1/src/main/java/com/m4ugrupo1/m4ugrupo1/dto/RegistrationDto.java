package com.m4ugrupo1.m4ugrupo1.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.m4ugrupo1.m4ugrupo1.model.Course;
import com.m4ugrupo1.m4ugrupo1.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegistrationDto {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	
	private Date registration;
	private User user;
	private Course course;
	
}