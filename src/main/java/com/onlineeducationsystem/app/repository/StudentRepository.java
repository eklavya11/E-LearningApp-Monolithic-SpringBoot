package com.onlineeducationsystem.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineeducationsystem.app.entity.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	Student findByUsername(String username);
	Student findByEmail(String email);
//	Student findByStudentId(int studentId);

}
