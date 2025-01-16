package com.kavya.junitdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import com.kavya.junitdemo.model.Course;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JunitdemoApplicationTests {

	@LocalServerPort
	private int port;

	private String baseurl = "http://localhost";

	private static RestTemplate restTemplate;

	@Autowired
	private TestH2Repo repo;

	@BeforeAll
	public static void init(){
		restTemplate = new RestTemplate();
	}
	
	@BeforeEach
	public void setup(){
		baseurl = baseurl.concat(":").concat(port+"").concat("/course");
	}

	@Test
	public void testAddCourse(){
		Course response = restTemplate.postForObject(baseurl+"/save",new Course(11,"BOOK11",11000),
		Course.class);
		assertEquals("BOOK11",response.getName());
		assertEquals(1, repo.findAll().size());
	}

	@Test
	@Sql(statements = "insert into course (id,name,price) values(10,'ABC',19090)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from course where id=10", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetCourses(){
		List<Course> response = restTemplate.getForObject(baseurl+"/getCources", List.class);
	//	assertEquals(1, response.size());
	System.out.println(response);
		assertEquals(2, repo.findAll().size());
	}

	@Test
	@Sql(statements = "insert into course (id,name,price) values(100,'ABCD',19090)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from course where id=100", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetCourseByName(){
		Course response = restTemplate.getForObject(baseurl+"/getCourseByName"+"/{name}",
		 Course.class,"ABCD");
		assertNotNull(response);
		assertEquals("ABCD", response.getName());
		assertEquals(1, repo.findAll().size());
	}

	@Test
	@Sql(statements = "insert into course (id,name,price) values(100,'ABCD',19090)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void testDeleteCourse(){
		assertEquals(1, repo.findAll().size());
		restTemplate.delete(baseurl+"/remove"+"/{id}",100);
		assertEquals(0, repo.findAll().size());
	}
	@Test
	@Sql(statements = "insert into course (id,name,price) values(101,'AB',19090)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from course where id=101", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateCourse(){
		Course co = new Course(101,"AB",19090);
		restTemplate.put(baseurl+"/updateCourse", co,"");
		//assertEquals("ABCD", response.getName());
		assertEquals(1, repo.findAll().size());
	}

}
