package com.yagobbosa.forum.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.yagobbosa.forum.model.Course;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CourseRepositoryTest {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	public void returnCourse() {
		String courseName = "HTML 5";

		Course html5 = new Course();
		html5.setName(courseName);
		html5.setCategory("Programming");

		testEntityManager.persist(html5);

		Course course = courseRepository.findByName(courseName);
		Assert.assertNotNull(course);
		Assert.assertEquals(courseName, course.getName());
	}

	@Test
	public void doesNotReturnCourse() {
		String courseName = "JPA";
		Course course = courseRepository.findByName(courseName);
		Assert.assertNull(course);
	}

}
