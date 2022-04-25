package com.yagobbosa.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.yagobbosa.forum.model.Course;
import com.yagobbosa.forum.model.Topic;
import com.yagobbosa.forum.repository.CourseRepository;

public class TopicForm {

	@NotNull
	@NotEmpty
	private String title;

	@NotNull
	@NotEmpty
	private String message;

	@NotNull
	@NotEmpty
	private String courseName;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Topic toConvert(CourseRepository courseRepository) {
		Course course = courseRepository.findByName(courseName);

		return new Topic(title, message, course);
	}

}
