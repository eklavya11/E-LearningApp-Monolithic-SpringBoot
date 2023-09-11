package com.onlineeducationsystem.app.service;

import java.util.List;


import com.onlineeducationsystem.app.dto.LoginDto;
import com.onlineeducationsystem.app.dto.LoginResultDto;
import com.onlineeducationsystem.app.entity.Student;
import com.onlineeducationsystem.app.exceptions.StudentException;



public interface StudentService {
	
	public Student deleteStudent(int id) throws StudentException;
	public Student viewStudentById(int id) throws StudentException;
	public Student viewStudentByEmail(String email) throws StudentException;
	public List<Student> getAllStudentsList();
	public Student registerStudent( Student student) throws StudentException;
	public Student updateStudent(Student student, int id) throws StudentException;
	public LoginResultDto validateStudent(LoginDto loginDto) throws StudentException;
	public String login(LoginDto loginDto)throws StudentException;

}
