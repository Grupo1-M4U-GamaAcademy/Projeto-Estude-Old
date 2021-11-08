package com.m4ugrupo1.m4ugrupo1.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "registration")
public class Registration {
	
	@Id
	@Column(name = "registration_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long registration_id;
	
	@Column(name = "registration_date")
	private LocalDate registrationDate;
	
	
	@OneToOne(targetEntity=User.class, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@OneToOne(targetEntity=Course.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;
	

	public Registration(LocalDate date, User user, Course course) {
		super();
		this.registrationDate = date;
		this.user = user;
		this.course = course;
	}
	

	
}
