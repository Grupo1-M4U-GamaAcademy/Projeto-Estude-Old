package com.m4ugrupo1.m4ugrupo1.service.core.impl;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m4ugrupo1.m4ugrupo1.authentication.UserRepository;
import com.m4ugrupo1.m4ugrupo1.model.Course;
import com.m4ugrupo1.m4ugrupo1.model.Registration;
import com.m4ugrupo1.m4ugrupo1.model.User;
import com.m4ugrupo1.m4ugrupo1.repository.CourseRepository;
import com.m4ugrupo1.m4ugrupo1.repository.RegistrationRepository;

@Service
public class RegistrationServiceImpl {
	
    @Autowired
	private RegistrationRepository registrationRepository;
    @Autowired
	private CourseRepository courseRepository;
    @Autowired
	private UserRepository userRepository;
    @Autowired
	private LocalDate registrationDate;

	@Autowired
	public RegistrationServiceImpl(RegistrationRepository registrationRepository, CourseRepository courseRepository,
			UserRepository userRepository) {
		this.registrationRepository = registrationRepository;
		this.courseRepository = courseRepository;
		this.userRepository = userRepository;
	}

	public void createRegistration(Long course_id, String username) throws Exception {
		Course course = courseRepository.findById(course_id).get();
		User user = userRepository.findByUsername(username);

		if (null != registrationRepository.findByCourseAndUser(course, user)) {
			throw new Exception("Student is already enrolled in this course");
		}

		LocalDate date = LocalDate.now();
		Registration registration = new Registration();
		registrationRepository.save(registration);
	}

}
