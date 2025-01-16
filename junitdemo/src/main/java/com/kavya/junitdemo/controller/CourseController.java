package com.kavya.junitdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.kavya.junitdemo.model.Course;
import com.kavya.junitdemo.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/getCources")
    public ResponseEntity<List<Course>> getCourse(){
        List<Course> courses = courseService.getAllCourses();
    return new ResponseEntity<>(courses, HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<Course> addCourse(@RequestBody Course course){
        Course c = courseService.addCourse(course);
    return new ResponseEntity<>(c, HttpStatus.OK);
    }
    @GetMapping("/getCourseByName/{name}")
    public ResponseEntity<Course> getCourseByName(@PathVariable String name){
        Course course = courseService.getCourseByName(name);
    return new ResponseEntity<>(course, HttpStatus.OK);
    }
    @DeleteMapping("/remove")
    public ResponseEntity<Course> deleteCourse(@RequestBody Course course){
     courseService.deleteCourse(course);
    return new ResponseEntity<>(course, HttpStatus.OK);
    }

}
