package com.m4ugrupo1.m4ugrupo1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeachersDto {
	
	private String name;
	private String lastName;
	private String email;
	private String descriptionTeachers;
	private String detailsTeachers;
	private String imageUrl;
	
}