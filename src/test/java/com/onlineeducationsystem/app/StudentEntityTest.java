package com.onlineeducationsystem.app;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.onlineeducationsystem.app.entity.Student;

public class StudentEntityTest {

//    @Test
//    public void testValidStudentCreation() {
//        Student student = new Student(1, "john_doe", "password123", "John", "Doe", "john@example.com", "9876543210");
//        assertNotNull(student);
//        assertEquals("john_doe", student.getUsername());
//        assertEquals("password123", student.getPassword());
//        assertEquals("John", student.getFirstname());
//        assertEquals("Doe", student.getLastname());
//        assertEquals("john@example.com", student.getEmail());
//        assertEquals("9876543210", student.getPhone());
//    }

    

    @Test
    public void testStudentGettersAndSetters() {
        Student student = new Student();
        student.setId(2);
        student.setUsername("jane_doe");
        student.setPassword("securepass");
        student.setFirstname("Jane");
        student.setLastname("Doe");
        student.setEmail("jane@example.com");
        student.setPhone("9876543210");

        assertEquals(2, student.getId());
        assertEquals("jane_doe", student.getUsername());
        assertEquals("securepass", student.getPassword());
        assertEquals("Jane", student.getFirstname());
        assertEquals("Doe", student.getLastname());
        assertEquals("jane@example.com", student.getEmail());
        assertEquals("9876543210", student.getPhone());
    }
}
