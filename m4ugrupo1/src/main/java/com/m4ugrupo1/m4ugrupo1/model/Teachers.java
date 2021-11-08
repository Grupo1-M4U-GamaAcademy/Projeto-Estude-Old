package com.m4ugrupo1.m4ugrupo1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "teachers")
public class Teachers {
	
	@Id
	@Column(name = "teachers_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long teachers_id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "description")
	private String descriptionTeachers;
	
	@Column(name = "details")
	private String detailsTeachers;
	
	private String imageUrl;
	
	
		

	
	public Teachers(String name, String lastName, String email, String descriptionTeachers, String imageUrl) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.descriptionTeachers = descriptionTeachers;
		this.imageUrl = imageUrl;
	}

	
	
	public Teachers (String detailsTeachers) {
		this.detailsTeachers = detailsTeachers;

}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getDescriptionTeachers() {
		return descriptionTeachers;
	}



	public void setDescriptionTeachers(String descriptionTeachers) {
		this.descriptionTeachers = descriptionTeachers;
	}



	public String getDetailsTeachers() {
		return detailsTeachers;
	}



	public void setDetailsTeachers(String detailsTeachers) {
		this.detailsTeachers = detailsTeachers;
	}



	public String getImageUrl() {
		return imageUrl;
	}



	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}



	public long getTeachers_id() {
		return teachers_id;
	}



	public void setTeachers_id(long teachers_id) {
		this.teachers_id = teachers_id;
	}
	
	}
	

