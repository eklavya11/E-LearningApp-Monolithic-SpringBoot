package com.onlineeducationsystem.app.repository;

 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 

import com.onlineeducationsystem.app.entity.CourseEntity;

 

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {

 

}