package com.yagobbosa.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yagobbosa.forum.controller.dto.TopicDto;
import com.yagobbosa.forum.model.Topic;
import com.yagobbosa.forum.repository.TopicRepository;

@RestController
public class TopicsController {

	@Autowired
	private TopicRepository topicRepository;

	@RequestMapping("/topics")
	public List<TopicDto> list() {
		List<Topic> topic = topicRepository.findAll();

		return TopicDto.toConvert(topic);
	}

}
