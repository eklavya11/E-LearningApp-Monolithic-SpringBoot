package com.onlineeducationsystem.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.onlineeducationsystem.app.entity.Student;
import com.onlineeducationsystem.app.exceptions.StudentException;
import com.onlineeducationsystem.app.repository.StudentRepository;
import com.onlineeducationsystem.app.service.StudentService;

import antlr.collections.List;

@SpringBootTest
public class StudentServiceTest {
	
	@Autowired
	private StudentService studentService;
	@Autowired
	private StudentRepository studentRepository;
	
	 
	 
//	 @BeforeEach
//	 void createDeleteStudent() {
//		 Student student = new Student(950,"testuser", "password3", "Johne", "Doee", "johne@example.com", "9834567890");
//	        studentRepository.save(student);
//	        
//	 }
//	 
//	 @AfterEach
//	 void deleteRegisterStudent() throws StudentException {
//		 Student result = studentService.viewStudentByEmail("testuser@gmail.com");
//			if (result != null) {
//				studentRepository.delete(result);
//				
//			}
//	 }
	 
	 
	 @Test
	    void testUpdateNonExistingStudent() {
	        // Arrange
	        int nonExistingStudentId = 999;
	        Student student = new Student();

	        // Act and Assert
	        assertThrows(StudentException.class, () -> studentService.updateStudent(student, nonExistingStudentId));
	    }
	 
	 @Test
	    void testDeleteStudent() throws StudentException {
	        // Arrange
	        String email = "johne@example.com";
	        Student result = studentService.viewStudentByEmail(email);
	        studentRepository.delete(result);

	        // Act
	        

	        // Assert
	        assertNotNull(result);
	        assertThrows(StudentException.class, () -> studentService.viewStudentByEmail(email));
	    }
	 
	 @Test
	    void testDeleteNonExistingStudent() {
	        // Arrange
	        int nonExistingStudentId = 999;

	        // Act and Assert
	        assertThrows(StudentException.class, () -> studentService.deleteStudent(nonExistingStudentId));
	    }
	 
	 @Test
	    void testGetAllStudentsList() {
	        // Act
	        List students = (List) studentService.getAllStudentsList();

	        // Assert
	        assertNotNull(students);
	        assertTrue(((java.util.List<Student>) students).size() > 0);
	    }
	 
//	 @Test
//	    void testRegisterStudent() throws StudentException {
//	        // Arrange
//		 Student student = new Student(901, "username334", "passwoxe", "Johner", "Doe", "testuser@gmail.com", "9234567890");
//	        studentRepository.save(student);
//	        Student registeredStudent = studentService.registerStudent(student);
//
//	        // Assert
//	        assertNotNull(registeredStudent);
////	        assertNotNull(registeredStudent.getId());
//	        assertEquals("username334", registeredStudent.getUsername());
//	        assertEquals("Johner", registeredStudent.getFirstname());
//	        assertEquals("Doe", registeredStudent.getLastname());
//	        assertEquals("testuser@gmail.com", registeredStudent.getEmail());
//	        assertEquals("9234567890", registeredStudent.getPhone());
//	    }
	 
	 @Test
	    void testViewExistingStudentById() throws StudentException {
	        // Arrange
	        int existingStudentId = 3;

	        // Act
	        Student student = studentService.viewStudentById(existingStudentId);

	        // Assert
	        assertNotNull(student);
	        assertEquals(existingStudentId, student.getId());
	    }
	 
	 @Test
	    void testViewNonExistingStudentById() {
	        // Arrange
	        int nonExistingStudentId = 999;

	        // Act and Assert
	        assertThrows(StudentException.class, () -> studentService.viewStudentById(nonExistingStudentId));
	    }


}
