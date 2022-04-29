package com.yagobbosa.forum.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.yagobbosa.forum.model.Course;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CourseRepositoryTest {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	public void hasCourse() {
		String courseName = "HTML 5";

		Course html5 = new Course();
		html5.setName(courseName);
		html5.setCategory("Programming");

		testEntityManager.persist(html5);

		Course course = courseRepository.findByName(courseName);
	}

	@Test
	public void hasNoCourse() {
		String courseName = "JPA";
		Course course = courseRepository.findByName(courseName);
	}

}
