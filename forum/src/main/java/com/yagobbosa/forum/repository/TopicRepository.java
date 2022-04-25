package com.yagobbosa.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yagobbosa.forum.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

}
