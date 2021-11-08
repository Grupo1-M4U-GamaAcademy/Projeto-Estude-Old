package com.m4ugrupo1.m4ugrupo1.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
	
	private String userName;
	private String password;
	private String name;
	private String lastName;
	private String details;
	private String email;
	private String date;
	private MultipartFile imageUrl;

}
