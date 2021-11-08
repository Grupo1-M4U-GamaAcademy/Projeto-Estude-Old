package com.m4ugrupo1.m4ugrupo1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course")
public class Course {
	
	@Id
	@Column(name = "course_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_course;
	
	@Column(name = "name", nullable = false, unique = true)
	private String nameCourse;
	
	@Column(name = "description")
	private String descriptionCourse;
	
	@Column(name = "details")
	private String detailsCourse;
	
	@Column(name = "difficulty")
	private String difficultyCourse;
	
	@Column(name = "url")
	private String urlCourse;
	
	
	private String imageCourse;
	
	@ManyToOne(targetEntity=Teachers.class, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "teachers_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Teachers teachers;
	

	public Course(String nameCourse, String descriptionCourse, String detailsCourse, String difficultyCourse,
			String urlCourse, String imageCourse, Teachers teachers) {
		super();
		this.nameCourse = nameCourse;
		this.descriptionCourse = descriptionCourse;
		this.detailsCourse = detailsCourse;
		this.difficultyCourse = difficultyCourse;
		this.urlCourse = urlCourse;
		this.imageCourse = imageCourse;
		this.teachers = teachers;
	}

	public String getNameCourse() {
		return nameCourse;
	}

	public void setNameCourse(String nameCourse) {
		this.nameCourse = nameCourse;
	}

	public String getDescriptionCourse() {
		return descriptionCourse;
	}

	public void setDescriptionCourse(String descriptionCourse) {
		this.descriptionCourse = descriptionCourse;
	}

	public String getDetailsCourse() {
		return detailsCourse;
	}

	public void setDetailsCourse(String detailsCourse) {
		this.detailsCourse = detailsCourse;
	}

	public String getDifficultyCourse() {
		return difficultyCourse;
	}

	public void setDifficultyCourse(String difficultyCourse) {
		this.difficultyCourse = difficultyCourse;
	}

	public String getUrlCourse() {
		return urlCourse;
	}

	public void setUrlCourse(String urlCourse) {
		this.urlCourse = urlCourse;
	}

	public String getImageCourse() {
		return imageCourse;
	}

	public void setImageCourse(String imageCourse) {
		this.imageCourse = imageCourse;
	}

	public Teachers getTeachers() {
		return teachers;
	}

	public void setTeachers(Teachers teachers) {
		this.teachers = teachers;
	}
	
	
	
	
	
}
