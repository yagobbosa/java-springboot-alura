package com.yagobbosa.forum.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.yagobbosa.forum.controller.dto.TopicDetailsDto;
import com.yagobbosa.forum.controller.dto.TopicDto;
import com.yagobbosa.forum.controller.form.TopicForm;
import com.yagobbosa.forum.model.Topic;
import com.yagobbosa.forum.repository.CourseRepository;
import com.yagobbosa.forum.repository.TopicRepository;

@RestController
@RequestMapping("/topics")
public class TopicsController {

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	private CourseRepository courseRepository;

	@GetMapping
	public List<TopicDto> list(String courseName) {
		if (courseName == null) {
			List<Topic> topics = topicRepository.findAll();
			return TopicDto.toConvert(topics);
		} else {
			List<Topic> topics = topicRepository.findByCourseName(courseName);
			return TopicDto.toConvert(topics);
		}
	}

	@PostMapping
	public ResponseEntity<TopicDto> register(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriBuilder) {
		Topic topic = form.toConvert(courseRepository);
		topicRepository.save(topic);

		URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();

		return ResponseEntity.created(uri).body(new TopicDto(topic));
	}

	@GetMapping("/{id}")
	public TopicDetailsDto details(@PathVariable Long id) {
		Topic topic = topicRepository.getById(id);

		return new TopicDetailsDto(topic);
	}

}
