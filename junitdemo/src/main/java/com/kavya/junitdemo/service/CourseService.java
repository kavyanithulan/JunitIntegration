package com.kavya.junitdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kavya.junitdemo.model.Course;
import com.kavya.junitdemo.repo.CourseRepository;

@Service
public class CourseService {
    @Autowired
    private CourseRepository repo;
    public List<Course> getAllCourses(){        
        return repo.findAll();
    }
    public Course addCourse(Course course){        
        return repo.save(course);
    }
    public Course getCourseByName(String name){        
        return repo.findByName(name);
    }
    public void deleteCourse(int id){        
         repo.deleteById(id);
    }
    public Course updateCourse(Course course){  
        return repo.save(course);
    }
    public Course updatePatchCourseById(Course course,int id){
        Course c = repo.findById(id).get();     
        return repo.save(c);
    }
}
