package com.onlineeducationsystem.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.onlineeducationsystem.app.repository.StudentRepository;
import com.onlineeducationsystem.app.service.StudentService;

@SpringBootTest
public class StudentControllerTest {
	
	@Autowired
	private StudentService studentService;
	@Autowired
	private StudentRepository studentRepository;

}
