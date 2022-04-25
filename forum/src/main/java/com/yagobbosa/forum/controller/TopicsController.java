package com.yagobbosa.forum.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.yagobbosa.forum.controller.dto.TopicDetailsDto;
import com.yagobbosa.forum.controller.dto.TopicDto;
import com.yagobbosa.forum.controller.form.TopicForm;
import com.yagobbosa.forum.controller.form.TopicFormUpdate;
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
	public Page<TopicDto> list(@RequestParam(required = false) String courseName, int page,
			int numberofElementsPerPage) {

		Pageable pageable = PageRequest.of(page, numberofElementsPerPage);

		if (courseName == null) {
			Page<Topic> topics = topicRepository.findAll(pageable);
			return TopicDto.toConvert(topics);
		} else {
			Page<Topic> topics = topicRepository.findByCourseName(courseName, pageable);
			return TopicDto.toConvert(topics);
		}
	}

	@PostMapping
	@Transactional
	public ResponseEntity<TopicDto> register(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriBuilder) {
		Topic topic = form.toConvert(courseRepository);
		topicRepository.save(topic);

		URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();

		return ResponseEntity.created(uri).body(new TopicDto(topic));
	}

	@GetMapping("/{id}")
	public ResponseEntity<TopicDetailsDto> detail(@PathVariable Long id) {
		Optional<Topic> optional = topicRepository.findById(id);

		if (optional.isPresent())
			return ResponseEntity.ok(new TopicDetailsDto(optional.get()));

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicDto> toUpdate(@PathVariable Long id, @RequestBody @Valid TopicFormUpdate form) {
		Optional<Topic> optional = topicRepository.findById(id);

		if (optional.isPresent()) {
			Topic topic = form.toUpdate(id, topicRepository);

			return ResponseEntity.ok(new TopicDto(topic));
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> toRemove(@PathVariable Long id) {
		Optional<Topic> optional = topicRepository.findById(id);

		if (optional.isPresent()) {
			topicRepository.deleteById(id);

			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

}
