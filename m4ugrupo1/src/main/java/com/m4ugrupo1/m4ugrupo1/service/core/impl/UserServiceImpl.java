package com.m4ugrupo1.m4ugrupo1.service.core.impl;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.m4ugrupo1.m4ugrupo1.authentication.AuthGroup;
import com.m4ugrupo1.m4ugrupo1.authentication.AuthGroupRepository;
import com.m4ugrupo1.m4ugrupo1.authentication.UserRepository;
import com.m4ugrupo1.m4ugrupo1.dto.UserDto;
import com.m4ugrupo1.m4ugrupo1.model.User;
import com.m4ugrupo1.m4ugrupo1.service.aws.AmazonS3ClientService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl {
	
	private AmazonS3ClientService amazonS3ClientService;
	private UserRepository userRepository;
	private AuthGroupRepository authGroupRepository;
	
	@Autowired
	public UserServiceImpl(AmazonS3ClientService amazonS3ClientService, UserRepository userRepository, AuthGroupRepository authGroupRepository) {
		this.amazonS3ClientService = amazonS3ClientService;
		this.userRepository = userRepository;
		this.authGroupRepository = authGroupRepository;
	}
	
	public void createUser(UserDto userDto, MultipartFile image) throws Exception {
		if (null != userRepository.findByUsername(userDto.getUserName())) {
			throw new Exception("Username Already exists " + userDto.getUserName());
		} else if (null != userRepository.findByEmail(userDto.getEmail())) {
			throw new Exception("User e-mail already exist" + userDto.getEmail());
		}
		
		String username = userDto.getUserName();
		String password = new BCryptPasswordEncoder(11).encode(userDto.getPassword());
		String name = userDto.getName();
		String lastName = userDto.getLastName();
		String email = userDto.getEmail();
		log.info("Getting image");
		log.info("about to upload");
		
		String imageUrl = amazonS3ClientService.toString();
		LocalDate date = LocalDate.now();
		User user = new User(username, password, name, lastName, email, imageUrl, date);
		AuthGroup group = new AuthGroup();
		
		group.setUsername(userDto.getUserName());
		group.setAuthGroup("USER");
		
		userRepository.save(user);
		authGroupRepository.save(group);
	}
	
	public void update(User user) {
		User current = userRepository.findByUsername(user.getUserName());
		
		current.setName(user.getName());
		current.setLastName(user.getLastName());
		current.setEmail(user.getEmail());
		current.setImageUrl(user.getImageUrl());
		
		userRepository.save(current);
	}
	public void patch(User user) {
		User current = userRepository.findByUsername(user.getUserName());
		
		current.setDetails(user.getDetails());
		
		userRepository.save(current);
		
	}
	
	public static File convert(MultipartFile file) throws IOException{
		File convertFile = new File(file.getOriginalFilename());
		convertFile.createNewFile();
		FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
		fileOutputStream.write(file.getBytes());
		fileOutputStream.close();
		return convertFile;
	}
	
	}

	
	


