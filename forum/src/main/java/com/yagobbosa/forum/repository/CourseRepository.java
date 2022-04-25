package com.yagobbosa.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yagobbosa.forum.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

	Course findByName(String name);

}
