package com.onlineeducationsystem.app.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.onlineeducationsystem.app.dto.LoginDto;
import com.onlineeducationsystem.app.dto.LoginResultDto;
import com.onlineeducationsystem.app.entity.Student;
import com.onlineeducationsystem.app.exceptions.StudentException;
import com.onlineeducationsystem.app.repository.StudentRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student deleteStudent(int id) throws StudentException {
		// TODO Auto-generated method stub
		try {
			Student result = studentRepository.findById(id).get();
			if (result != null) {
				studentRepository.delete(result);
				return result;
			}
		} catch (NoSuchElementException e) {
			throw new StudentException("Student with student id:" + id + " not found!!!");
		}
		return null;

	}



	@Override
	public Student viewStudentById(int id) throws StudentException {
		try {
			Student result = studentRepository.findById(id).get();
			if (result != null) {
				return result;
			}
		} catch (NoSuchElementException e) {
			throw new StudentException("Student with customer id:" + id + " not found!!!");
		}
		return null;
	}

	@Override
	public List<Student> getAllStudentsList() {
		return studentRepository.findAll();
	}

	@Override
	public Student registerStudent(Student student) throws StudentException {
		// TODO Auto-generated method stub
		if(studentRepository.findByUsername(student.getUsername())!=null) {
			throw new StudentException("Username already exists,enter another username");
		}


		if(studentRepository.findByEmail(student.getEmail())!=null) {
			throw new StudentException("Email already exists,enter another email");
		}

		return studentRepository.save(student);
	}

	@Override
	public Student updateStudent(Student student, int id) throws StudentException {
		try {
			Student result = studentRepository.findById(id).get();
			if (result != null) {
				return studentRepository.save(student);
			}
		} catch (NoSuchElementException e) {
			throw new StudentException("Student with student id:" + id + " not found!!!");
		}
		return null;
	}



	@Override
	public Student viewStudentByEmail(String email) throws StudentException {
		// TODO Auto-generated method stub
		try {
			Student result = studentRepository.findByEmail(email);
			if (result != null) {
				return result;
			}
			else {
				throw new StudentException("Student with email:" + email + " not found!!!");
			}
		} catch (NoSuchElementException e) {
			throw new StudentException("Student with email:" + email + " not found!!!");
		}


	}



	@Override
	public LoginResultDto validateStudent(LoginDto loginDto) throws StudentException {
		LoginResultDto loginResultDto = new LoginResultDto();
		Student student = studentRepository.findByEmail(loginDto.getEmail());
		if (student != null) {
			if (student.getEmail().equals(loginDto.getEmail())
					&& student.getPassword().equals(loginDto.getPassword())) {
				loginResultDto.setId(student.getId());
				loginResultDto.setLoginMessage("SUCCESSFULY LOGIN!!!");
				loginResultDto.setEmail(loginDto.getEmail());
				return loginResultDto;
			} else {
				throw new StudentException("Student with username:" + loginDto.getEmail() + " not found!!!");
			}
		} else {
			loginResultDto.setLoginMessage("Invalid User");

		}
		return loginResultDto;
	}



	@Override
	public String login(LoginDto loginDto) throws StudentException {
		Student student = studentRepository.findByEmail(loginDto.getEmail());
		if(student==null)
			throw new StudentException("Student does't exists with given email/user id.");

		if (!student.getUsername().equals(loginDto.getEmail()) && !student.getPassword().equals(loginDto.getPassword()))
			throw new StudentException("Password and UserName does't match ");

		String issuer = student.getEmail();
		Date expiry = new Date(System.currentTimeMillis() + (60 * 1000));

		return  Jwts.builder().setIssuer(issuer).setExpiration(expiry)
				.signWith(SignatureAlgorithm.HS512, "Secret123").compact();
	}

}
