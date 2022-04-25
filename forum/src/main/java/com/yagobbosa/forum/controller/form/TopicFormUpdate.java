package com.yagobbosa.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.yagobbosa.forum.model.Topic;
import com.yagobbosa.forum.repository.TopicRepository;

public class TopicFormUpdate {

	@NotNull
	@NotEmpty
	private String title;

	@NotNull
	@NotEmpty
	private String message;

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

	public Topic toUpdate(Long id, TopicRepository topicRepository) {
		Topic topic = topicRepository.getById(id);

		topic.setTitle(this.title);
		topic.setMessage(this.message);

		return topic;
	}

}
