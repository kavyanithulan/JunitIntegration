package com.kavya.junitdemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kavya.junitdemo.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer>{

    Course findByName(String name);
    
}
