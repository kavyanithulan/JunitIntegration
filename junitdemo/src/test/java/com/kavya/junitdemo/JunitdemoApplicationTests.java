package com.kavya.junitdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.kavya.junitdemo.model.Course;
import com.kavya.junitdemo.repo.CourseRepository;
import com.kavya.junitdemo.service.CourseService;

@SpringBootTest
class JunitdemoApplicationTests {
	@Autowired
	private CourseService courseService;
	@MockitoBean
	private CourseRepository repo;

	@Test
	public void getAllCoursesTest(){
		when(repo.findAll()).thenReturn(Stream.of(new Course(64l,"book6",800),
		new Course(61l,"book61",800),new Course(65l,"book65",800)).
		collect(Collectors.toList()));
		assertEquals(3, courseService.getAllCourses().size());
	}
	
	@Test
	public void getCourseByNameTest(){
		String name="Book7";
		Course course = new Course(64l,"book7",800);
		when(repo.findByName(name)).thenReturn(course);
		assertEquals(course, courseService.getCourseByName("Book7"));
	}

	@Test
	public void addCourseTest(){
		Course course = new Course(64l,"book7",800);
		when(repo.save(course)).thenReturn(course);
		assertEquals(course, courseService.addCourse(course));
	}
	@Test
	public void deleteCourseTest(){
		Course course = new Course(64l,"book7",800);
		courseService.deleteCourse(course);
		verify(repo,times(1)).delete(course);
	}
}
