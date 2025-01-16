package com.kavya.junitdemo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kavya.junitdemo.model.Course;

@Repository
public interface TestH2Repo extends JpaRepository<Course,Integer>{
    
}
