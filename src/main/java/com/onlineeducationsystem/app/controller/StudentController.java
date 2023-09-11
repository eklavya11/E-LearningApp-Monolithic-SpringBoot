package com.onlineeducationsystem.app.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.onlineeducationsystem.app.dto.LoginDto;
import com.onlineeducationsystem.app.dto.LoginResultDto;
import com.onlineeducationsystem.app.dto.Response;
import com.onlineeducationsystem.app.entity.Student;
import com.onlineeducationsystem.app.exceptions.StudentException;
import com.onlineeducationsystem.app.service.StudentService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

@RestController
@CrossOrigin(origins = "*")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/student/register-student")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Student> insertCustomer(@RequestBody @Valid Student student) throws StudentException{
		Student result = studentService.registerStudent(student);
		if(result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/student/update-student/{Id}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Student> updateStudent(@RequestBody @Valid Student student,@PathVariable int Id) throws StudentException{
		Student result = studentService.updateStudent(student, Id);
		if(result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/admin/delete-student/{Id}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<?> deleteStudent(@PathVariable int Id) throws StudentException{
		Student result = studentService.deleteStudent(Id);
		if(result != null) {
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getAll/students")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<List<Student>> getAllStudentList()  {
		List<Student> AllStudentList = studentService.getAllStudentsList();
		return new ResponseEntity<>(AllStudentList, HttpStatus.OK);
	}
	
	@GetMapping("/admin/view-student-by-id/{Id}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Student> getStudentById(@PathVariable int Id) throws StudentException{
		Student result = studentService.viewStudentById(Id);
		if(result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/admin/view-student-by-email/{Email}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Student> getStudentByEmail(@PathVariable String Email) throws StudentException{
		Student result = studentService.viewStudentByEmail(Email);
		if(result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/validate/student/login")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Response> validateStudent(@RequestBody LoginDto LoginDto)  {

		Response response = new Response();
		try {
			LoginResultDto validateUserLogin = studentService.validateStudent(LoginDto);
			response.setStatus(Response.SUCCESS);
			response.setStatusCode(HttpStatus.OK);
			response.setResponse(validateUserLogin);
			return new ResponseEntity<Response>(response, response.getStatusCode());

		} catch (Exception e) {
			response.setStatus(Response.FAILURE);
			response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponse("LOGIN FAIL");
			return new ResponseEntity<Response>(response, response.getStatusCode());
		}

	}
	
	@PostMapping("login")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Response> loginTest(@RequestBody LoginDto user, HttpServletResponse response)throws StudentException{
		Response res = new Response();
		try {
			LoginResultDto validateUserLogin = studentService.validateStudent(user);
			res.setStatus(Response.SUCCESS);
			res.setStatusCode(HttpStatus.OK);
			res.setResponse(validateUserLogin);
			Cookie cookie = new Cookie("jwt", studentService.login(user));
			response.addCookie(cookie);
			return new ResponseEntity<Response>(res, res.getStatusCode());

		} catch (Exception e) {
			res.setStatus(Response.FAILURE);
			res.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setResponse("LOGIN FAIL");
			return new ResponseEntity<Response>(res, res.getStatusCode());
		}
	}
	
	@PostMapping("logout")
	@ResponseStatus(value = HttpStatus.CREATED)
	public String logout(HttpServletResponse response) {
		Cookie cookie = new Cookie("jwt", "");
		response.addCookie(cookie);
		return "Logout Success.";
	}
	
	@GetMapping("/View-Students-JWT")
	@ResponseStatus(value = HttpStatus.CREATED)
	public List<Student> viewStudents(@CookieValue("jwt") String jwt) throws StudentException {
		
		if(jwt == null)
			throw new StudentException("Unauthenticated !");
	
		try {
		Claims claim = Jwts.parser().setSigningKey("Secret123").parseClaimsJws(jwt).getBody();
		String email = claim.getIssuer();
		
		}
		catch(ExpiredJwtException e) {
			throw new StudentException("JWT got expired please try re loging. ");
		}
		 
		catch (Exception e) {
			throw new StudentException("Unauthenticated !");
		}
		
		return studentService.getAllStudentsList();
	}

}
