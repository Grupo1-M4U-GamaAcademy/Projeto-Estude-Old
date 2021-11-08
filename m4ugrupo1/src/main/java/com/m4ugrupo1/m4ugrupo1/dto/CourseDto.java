package com.m4ugrupo1.m4ugrupo1.dto;

import com.m4ugrupo1.m4ugrupo1.model.Teachers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseDto {
	
	private String nameCourse;
	private String descriptionCourse;
	private String difficultyCourse;
	private String detailsCourse;
	private String url;
	private String imageUrl;
	private Teachers teachers;
	
	public CourseDto changeToObeject() {
		return new CourseDto(nameCourse, descriptionCourse, 
				difficultyCourse, detailsCourse, url, imageUrl, teachers);
	}
	}

	

	